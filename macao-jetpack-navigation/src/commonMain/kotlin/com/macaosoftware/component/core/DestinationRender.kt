package com.macaosoftware.component.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.macaosoftware.component.drawer.DrawerStatePresenter

interface DestinationRender {

    fun getRenderType(): String

    @Composable
    fun Content(
        destinationInfo: DestinationInfo,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        resultAdapter: ResultAdapter<DestinationResult<*>>
    )

    fun getDrawerResultProcessor(
        drawer: DrawerStatePresenter,
        navController: NavHostController
    ): ResultProcessor
}
