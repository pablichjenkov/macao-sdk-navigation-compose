package com.macaosoftware.component.pager

import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.macaosoftware.component.core.Component
import com.macaosoftware.component.core.NavItem
import com.macaosoftware.component.core.NavigationComponent
import com.macaosoftware.component.core.Navigator
import com.macaosoftware.component.core.componentWithBackStackGetChildForNextUriFragment
import com.macaosoftware.component.core.deeplink.DeepLinkResult
import com.macaosoftware.component.core.destroyChildComponent
import com.macaosoftware.component.stack.BackstackInfo
import com.macaosoftware.component.util.EmptyNavigationComponentView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

/**
 * The PagerComponent keeps 3 started children components at the same time. It is necessary
 * as a warmup so the next Component.Content is started already when the user swipe.
 * */
class PagerComponent<out VM : PagerComponentViewModel> constructor(
    viewModelFactory: PagerComponentViewModelFactory<VM>,
    private var content: @Composable PagerComponent<VM>.(
        modifier: Modifier,
        pagerState: PagerState,
        childComponents: List<Component>
    ) -> Unit
) : Component(), NavigationComponent {

    val componentViewModel: VM = viewModelFactory.create(this)
    override val backStack = createBackStack(componentViewModel.pushStrategy)
    override val navigator = Navigator(backStack)
    override val backstackInfo = BackstackInfo()
    override var navItems: MutableList<NavItem> = mutableListOf()
    override var selectedIndex: Int = 0
    override var childComponents: MutableList<Component> = mutableListOf()
    override var activeComponent: MutableState<Component?> = mutableStateOf(null)
    private var currentActiveIndexSet = mutableSetOf<Int>()
    private var coroutineScope = CoroutineScope(componentViewModel.dispatchers.main)

    private val _componentOutFlow = MutableSharedFlow<PagerComponentOutEvent?>()
    val pagerComponentViewFlow: SharedFlow<PagerComponentOutEvent?>
        get() = _componentOutFlow

    private var currentPage = 0

    // region: ComponentLifecycle

    override fun onAttach() {
        componentViewModel.dispatchAttached()
    }

    override fun onActive() {
        println("${instanceId()}::onStart()")
        if (currentActiveIndexSet.isEmpty()) {
            if (childComponents.isNotEmpty()) {
                if (getComponent().startedFromDeepLink) {
                    return
                }
                activeComponent.value = childComponents[selectedIndex]
            } else {
                println("${instanceId()}::onStart() with childComponents empty")
            }
        } else {
            currentActiveIndexSet.forEach { activeChildIndex ->
                childComponents[activeChildIndex].dispatchActive()
            }
        }
        componentViewModel.dispatchStart()
    }

    override fun onInactive() {
        println("${instanceId()}::onStop()")
        currentActiveIndexSet.forEach { activeChildIndex ->
            childComponents[activeChildIndex].dispatchInactive()
        }
        coroutineScope.coroutineContext.cancelChildren()
        componentViewModel.dispatchStop()
    }

    override fun onDetach() {
        componentViewModel.dispatchDetach()
    }

    override fun handleBackPressed() {
        if (currentPage > 0) {
            selectPage(currentPage - 1)
        } else {
            if (componentViewModel.handleBackPressed()) {
                return
            }
            delegateBackPressedToParent()
        }
    }

    // endregion

    // region: NavigatorItems

    override fun getComponent(): Component {
        return this
    }

    override fun onSelectNavItem(selectedIndex: Int, navItems: MutableList<NavItem>) {
        activeComponent.value = childComponents[selectedIndex]
    }

    override fun updateSelectedNavItem(newTop: Component) {
        // The ViewPager updates the indicator automatically, no need to do anything here.
    }

    override fun onDetachChildComponent(component: Component) {
        destroyChildComponent()
    }

    // endregion

    // region: DeepLink

    override fun onDeepLinkNavigateTo(matchingComponent: Component): DeepLinkResult {
        val matchingComponentIndex = childComponents.indexOf(matchingComponent)
        selectPage(matchingComponentIndex)
        return DeepLinkResult.Success(matchingComponent)
    }

    override fun getChildForNextUriFragment(deepLinkPathSegment: String): Component? {
        return this.componentWithBackStackGetChildForNextUriFragment(
            deepLinkPathSegment
        )
    }

    // endregion

    private fun selectPage(pageIdx: Int) {
        coroutineScope.launch {
            _componentOutFlow.emit(PagerComponentOutEvent.SelectPage(pageIdx))
        }
    }

    private fun onPageChanged(pageIndex: Int) {
        println("${instanceId()}::onPageChanged newPage = $pageIndex")
        currentPage = pageIndex
        val nextStartedIndexSet = mutableSetOf<Int>()
        if (pageIndex - 1 >= 0) {
            nextStartedIndexSet.add(pageIndex - 1)
        }
        nextStartedIndexSet.add(pageIndex)
        if (pageIndex + 1 < childComponents.size) {
            nextStartedIndexSet.add(pageIndex + 1)
        }

        // TODO: Extract this to a helper class and Unit test it
        println("onPageChanged::currentStartedSet = $currentActiveIndexSet")
        println("onPageChanged::nextStartedSet = $nextStartedIndexSet")
        val keepStartedIndexSet = mutableSetOf<Int>()
        currentActiveIndexSet.forEach { index ->
            if (nextStartedIndexSet.contains(index)) {
                keepStartedIndexSet.add(index)
            } else {
                println("onPageChanged::stopping old index = $index")
                childComponents[index].dispatchInactive()
            }
        }

        println("onPageChanged::keepStartedIndexSet = $keepStartedIndexSet")
        val newStartingSet = nextStartedIndexSet.subtract(keepStartedIndexSet)
        println("onPageChanged::newStartingSet = $newStartingSet")
        newStartingSet.forEach { index ->
            childComponents[index].dispatchActive()
        }

        currentActiveIndexSet = nextStartedIndexSet
        selectedIndex = pageIndex
        activeComponent.value = childComponents[pageIndex]
        updateBackstack(pageIndex)
    }

    private fun updateBackstack(selectedIndex: Int) {
        backStack.deque.clear()
        backStack.deque.add(childComponents[selectedIndex])
    }

    // region Pager rendering

    @Composable
    override fun Content(modifier: Modifier) {
        val pagerState = rememberPagerState(initialPage = selectedIndex) {
            childComponents.size
        }
        println(
            """${instanceId()}.Composing() stack.size = ${backStack.size()}
                |currentPage = ${pagerState.currentPage}
                |lifecycleState = ${lifecycleState}
            """.trimMargin()
        )
        if (activeComponent.value != null) {
            content(modifier, pagerState, childComponents)
        } else {
            EmptyNavigationComponentView(this@PagerComponent)
        }
        LaunchedEffect(childComponents, pagerState) {
            launch {
                // Collect from the pager state a snapshotFlow reading the settledPage
                snapshotFlow { pagerState.settledPage }.collect { pageIndex ->
                    onPageChanged(pageIndex)
                }
            }
        }
    }

}
