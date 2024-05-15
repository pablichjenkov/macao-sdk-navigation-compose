package com.macaosoftware.component.topbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.macaosoftware.component.core.Component
import com.macaosoftware.component.stack.StackView
import com.macaosoftware.component.stack.StackViewAnimations

object TopBarComponentDefaults {

    fun createTopBarStatePresenter(
        topBarStyle: TopBarStyle = TopBarStyle()
    ): TopBarStatePresenterDefault {
        return TopBarStatePresenterDefault(topBarStyle = topBarStyle)
    }

    val TopBarComponentView: @Composable TopBarComponent<TopBarComponentViewModel>.(
        modifier: Modifier,
        activeChildComponent: Component
    ) -> Unit = { modifier, _ ->
        Scaffold(
            modifier = modifier,
            topBar = {
                TopBar(componentViewModel.topBarStatePresenter)
            }
        ) { paddingValues ->
            StackView(
                modifier = modifier.padding(paddingValues),
                backStack = backStack,
                lastBackstackEvent = lastBackstackEvent,
                onComponentSwipedOut = {
                    componentViewModel.topBarStatePresenter.onBackPressEvent()
                },
                stackViewAnimations = StackViewAnimations.Default
            )
        }
    }
}
