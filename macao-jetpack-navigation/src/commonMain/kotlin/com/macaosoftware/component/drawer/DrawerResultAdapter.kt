package com.macaosoftware.component.drawer

import androidx.navigation.NavHostController
import com.macaosoftware.component.core.DestinationResult
import com.macaosoftware.component.core.ResultAdapter

abstract class DrawerResultAdapter<T> : ResultAdapter<DestinationResult<T>> {

    lateinit var drawerStatePresenter: DrawerStatePresenter
    lateinit var navController: NavHostController
}
