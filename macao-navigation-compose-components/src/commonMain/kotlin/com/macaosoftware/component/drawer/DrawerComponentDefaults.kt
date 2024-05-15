package com.macaosoftware.component.drawer

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.macaosoftware.component.core.Component
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object DrawerComponentDefaults {

    fun createDrawerStatePresenter(
        dispatcher: CoroutineDispatcher = Dispatchers.Main,
        drawerStyle: DrawerStyle = DrawerStyle(),
        drawerHeaderState: DrawerHeaderState = DrawerHeaderDefaultState(
            title = "Header Title",
            description = "This is the default text. Provide your own text for your App",
            imageUri = "",
            style = drawerStyle
        )
    ): DrawerStatePresenterDefault {
        return DrawerStatePresenterDefault(
            dispatcher = dispatcher,
            drawerHeaderState = drawerHeaderState,
            drawerStyle = drawerStyle
        )
    }

    val DrawerComponentView: @Composable DrawerComponent<DrawerComponentViewModel>.(
        modifier: Modifier,
        childComponent: Component
    ) -> Unit = { modifier, childComponent ->

        navController = rememberNavController()

        NavigationDrawer(
            modifier = modifier,
            statePresenter = componentViewModel.drawerStatePresenter,
            navController = navController
        )
    }
}
