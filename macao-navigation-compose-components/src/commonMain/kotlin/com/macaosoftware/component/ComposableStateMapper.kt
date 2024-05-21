package com.macaosoftware.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

interface ComposableStateMapper {

    fun getInitialRoute(): String

    @Composable
    fun ContentForRoot(
        stateType: String
    )

    @Composable
    fun ContentForRoute(
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry
    )
}
