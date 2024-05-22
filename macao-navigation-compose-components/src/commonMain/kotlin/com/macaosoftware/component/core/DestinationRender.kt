package com.macaosoftware.component.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

interface DestinationRender {

    fun getNavItem(): NavItem

    @Composable
    fun Content(
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry
    )

}
