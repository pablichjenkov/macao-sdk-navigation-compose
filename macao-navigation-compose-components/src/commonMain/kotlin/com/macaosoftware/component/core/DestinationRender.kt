package com.macaosoftware.component.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

interface DestinationRender {

    fun getRoute(): String
    fun getRenderType(): String

    @Composable
    fun Content(
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry
    )

}
