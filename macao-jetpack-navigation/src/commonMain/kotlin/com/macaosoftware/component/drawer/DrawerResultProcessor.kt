package com.macaosoftware.component.drawer

import androidx.navigation.NavHostController
import com.macaosoftware.component.core.ResultProcessor

abstract class DrawerResultProcessor(
    private val drawerStatePresenter: DrawerStatePresenter,
    private val navController: NavHostController
) : ResultProcessor
