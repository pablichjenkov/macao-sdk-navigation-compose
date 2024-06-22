package com.macaosoftware.component.drawer

import androidx.navigation.NavHostController
import com.macaosoftware.component.core.DestinationResult
import com.macaosoftware.component.core.ResultHandler

abstract class DrawerResultHandler<T> : ResultHandler<DestinationResult<T>> {

    lateinit var drawerStatePresenter: DrawerStatePresenter
    lateinit var navController: NavHostController
}
