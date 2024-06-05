package com.macaosoftware.component.drawer

import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

interface DrawerStatePresenter {
    /**
     * Intended for the Composable NavigationDrawer to render the List if NavDrawer items
     * */
    val navItemsState: State<List<DrawerNavItem>>

    val drawerStyle: DrawerStyle

    /**
     * Intended for the Composable NavigationDrawer to close open/close the Drawer pane
     * */
    val drawerOpenFlow: SharedFlow<DrawerValue>

    /**
     * Intended for a client class to listen for navItem click events
     * */
    val navItemClickFlow: SharedFlow<DrawerNavItem>

    val drawerHeaderState: State<DrawerHeaderState>

    /**
     * Intended to be called from the Composable NavigationDrawer item click events
     * */
    fun navItemClick(drawerNavItem: DrawerNavItem)

    fun setNavItemsDeco(navItemDecoList: List<DrawerNavItem>)

    /**
     * Intended to be called from a client class to select a navItem in the drawer
     * */
    fun selectNavItemDeco(drawerNavItem: DrawerNavItem)

    /**
     * Intended to be called from a client class to toggle the drawer visibility
     * */
    fun setDrawerState(drawerValue: DrawerValue)
}

class DrawerStatePresenterDefault(
    dispatcher: CoroutineDispatcher,
    drawerHeaderState: DrawerHeaderState,
    override val drawerStyle: DrawerStyle = DrawerStyle(),
    navItemDecoList: List<DrawerNavItem> = emptyList()
) : DrawerStatePresenter {

    private val coroutineScope = CoroutineScope(dispatcher)

    private var _navItemsState = mutableStateOf(navItemDecoList)
    override val navItemsState: State<List<DrawerNavItem>> = _navItemsState

    override val drawerHeaderState: State<DrawerHeaderState> = mutableStateOf(drawerHeaderState)

    private val _drawerOpenFlow = MutableSharedFlow<DrawerValue>()
    override val drawerOpenFlow: SharedFlow<DrawerValue> = _drawerOpenFlow.asSharedFlow()

    private val _navItemClickFlow = MutableSharedFlow<DrawerNavItem>()
    override val navItemClickFlow: SharedFlow<DrawerNavItem> = _navItemClickFlow.asSharedFlow()

    override fun navItemClick(drawerNavItem: DrawerNavItem) {
        coroutineScope.launch {
            _drawerOpenFlow.emit(DrawerValue.Closed)
            _navItemClickFlow.emit(drawerNavItem)
        }
    }

    override fun setDrawerState(drawerValue: DrawerValue) {
        coroutineScope.launch {
            _drawerOpenFlow.emit(drawerValue)
        }
    }

    override fun setNavItemsDeco(navItemDecoList: List<DrawerNavItem>) {
        _navItemsState.value = navItemDecoList
    }

    /**
     * To be called by a client class when the Drawer selected item needs to be updated.
     * */
    override fun selectNavItemDeco(selectedNavItem: DrawerNavItem) {

        val update = _navItemsState.value.map { drawerNavItem ->

            val isSameItem =
                selectedNavItem.destinationInfo.route == drawerNavItem.destinationInfo.route

            drawerNavItem.copy(selected = isSameItem)
        }

        _navItemsState.value = update
    }

}
