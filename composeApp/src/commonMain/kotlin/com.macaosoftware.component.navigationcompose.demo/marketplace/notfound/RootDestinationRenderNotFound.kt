package com.macaosoftware.component.navigationcompose.demo.marketplace.notfound

import androidx.compose.runtime.Composable
import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.core.RootDestinationRender
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
import com.macaosoftware.component.util.MacaoDestinationRenderNotFoundView

class RootDestinationRenderNotFound : RootDestinationRender {

    override fun getRenderType(): String {
        return ServerUiConstants.ComponentType.DestinationNotFound
    }

    @Composable
    override fun Content(destinationInfo: DestinationInfo) {
        MacaoDestinationRenderNotFoundView(destinationInfo.renderType)
    }
}
