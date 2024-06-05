package com.macaosoftware.component.navigationcompose.demo.marketplace.notfound

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.core.DestinationRender
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
import com.macaosoftware.component.util.MacaoDestinationRenderNotFoundView

class DestinationRenderNotFound : DestinationRender {

    override fun getRenderType(): String {
        return ServerUiConstants.ComponentType.DestinationNotFound
    }

    @Composable
    override fun Content(
        destinationInfo: DestinationInfo,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry
    ) {
        MacaoDestinationRenderNotFoundView(destinationInfo.renderType)
    }
}
