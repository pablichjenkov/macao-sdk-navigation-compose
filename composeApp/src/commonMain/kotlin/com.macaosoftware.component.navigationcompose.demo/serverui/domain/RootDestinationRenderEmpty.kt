package com.macaosoftware.component.navigationcompose.demo.serverui.domain

import androidx.compose.runtime.Composable
import com.macaosoftware.component.core.RootDestinationRender
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
import com.macaosoftware.component.util.EmptyNavigationView

class RootDestinationRenderEmpty : RootDestinationRender {

    override fun getRoute(): String {
        return ServerUiConstants.Routes.GLOBAL_SCREEN_404
    }

    override fun getRenderType(): String {
        return ServerUiConstants.ComponentType.DestinationNotFound
    }

    @Composable
    override fun Content() {
        EmptyNavigationView()
    }

}