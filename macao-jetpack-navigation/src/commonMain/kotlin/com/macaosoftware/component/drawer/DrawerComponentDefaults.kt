package com.macaosoftware.component.drawer

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

}
