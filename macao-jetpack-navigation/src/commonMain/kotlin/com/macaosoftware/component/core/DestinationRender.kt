package com.macaosoftware.component.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

interface DestinationRender {

    fun getRenderType(): String

    @Composable
    fun Content(
        destinationInfo: DestinationInfo,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        resultProcessor: ResultProcessor
    )
}
