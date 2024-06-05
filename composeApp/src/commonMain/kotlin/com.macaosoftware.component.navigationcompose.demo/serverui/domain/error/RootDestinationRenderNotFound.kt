package com.macaosoftware.component.navigationcompose.demo.serverui.domain.error

import androidx.compose.runtime.Composable
import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.core.RootDestinationRender
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
import com.macaosoftware.component.util.DestinationRenderNotFoundView

class RootDestinationRenderNotFound : RootDestinationRender {

    override fun getRenderType(): String {
        return ServerUiConstants.ComponentType.DestinationNotFound
    }

    @Composable
    override fun Content(destinationInfo: DestinationInfo) {
        DestinationRenderNotFoundView(destinationInfo.renderType)
    }
}
