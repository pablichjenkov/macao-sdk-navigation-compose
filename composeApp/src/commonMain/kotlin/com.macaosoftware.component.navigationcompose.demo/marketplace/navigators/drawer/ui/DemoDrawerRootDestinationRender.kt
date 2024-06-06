package com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.ui

import androidx.compose.runtime.Composable
import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.core.RootDestinationRender
import com.macaosoftware.component.drawer.DrawerView
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
import org.koin.compose.viewmodel.koinViewModel

class DemoDrawerRootDestinationRender : RootDestinationRender {

    override fun getRenderType(): String {
        return ServerUiConstants.ComponentType.Drawer
    }

    @Composable
    override fun Content(rootDestinationInfo: DestinationInfo) {
        val viewModel = koinViewModel<DemoDrawerViewModel>()
        DrawerView(rootDestinationInfo, viewModel)
    }
}
