package com.macaosoftware.component.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.macaosoftware.component.core.Component
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object BottomNavigationComponentDefaults {

    fun createBottomNavigationStatePresenter(
        dispatcher: CoroutineDispatcher = Dispatchers.Main
    ): BottomNavigationStatePresenterDefault {
        return BottomNavigationStatePresenterDefault(
            dispatcher = dispatcher
        )
    }

    val BottomNavigationComponentView: @Composable BottomNavigationComponent<BottomNavigationComponentViewModel>.(
        modifier: Modifier,
        childComponent: Component
    ) -> Unit = { modifier, childComponent ->
        NavigationBottom(
            modifier = modifier,
            bottomNavigationStatePresenter = componentViewModel.bottomNavigationStatePresenter
        ) {
            childComponent.Content(Modifier)
        }
    }

}
