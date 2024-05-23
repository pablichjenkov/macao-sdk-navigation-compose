package com.macaosoftware.component.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.macaosoftware.component.core.NavItem

interface DestinationPresenter {

    fun getRoute(): String
    fun getNavItem(): NavItem

    @Composable
    fun Content(
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry
    )

}
