package com.macaosoftware.component.navigationcompose.demo.serverui.domain

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.macaosoftware.component.core.DestinationRender
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
import com.macaosoftware.component.util.EmptyNavigationView

class DestinationRenderEmpty : DestinationRender {

    override fun getRoute(): String {
        return ServerUiConstants.Routes.GLOBAL_SCREEN_404
    }

    override fun getRenderType(): String {
        return ServerUiConstants.ComponentType.DestinationNotFound
    }

    @Composable
    override fun Content(
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry
    ) {
        EmptyNavigationView()
    }
}