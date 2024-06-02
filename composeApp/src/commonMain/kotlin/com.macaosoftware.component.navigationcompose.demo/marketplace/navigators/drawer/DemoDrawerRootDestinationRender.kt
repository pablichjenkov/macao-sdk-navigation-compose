package com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer

import androidx.compose.runtime.Composable
import com.macaosoftware.component.core.RootDestinationRender
import com.macaosoftware.component.drawer.DrawerView
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
import org.koin.compose.viewmodel.koinViewModel

class DemoDrawerRootDestinationRender : RootDestinationRender {

    override fun getRoute(): String = ServerUiConstants.Routes.RootGraph.MainEntryPoint

    override fun getRenderType(): String = ServerUiConstants.ComponentType.Drawer

    @Composable
    override fun Content() {

        val viewModel = koinViewModel<DemoDrawerViewModel>()
        DrawerView(viewModel)
    }
}
