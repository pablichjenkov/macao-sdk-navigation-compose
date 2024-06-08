package com.macaosoftware.component.drawer

import androidx.navigation.NavHostController
import com.macaosoftware.component.core.ResultProcessor

abstract class DrawerResultProcessor : ResultProcessor {
    lateinit var drawerStatePresenter: DrawerStatePresenter
    lateinit var navController: NavHostController
}
