package com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelStoreOwner
import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.core.RootDestinationRender
import com.macaosoftware.component.drawer.DrawerView
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
import org.koin.compose.viewmodel.koinViewModel

class DemoDrawerRootDestinationRender : RootDestinationRender {

    override fun getRenderType(): String {
        return ServerUiConstants.ComponentType.DrawerDemo
    }

    @Composable
    override fun Content(
        destinationInfo: DestinationInfo,
        viewModelStoreOwner: ViewModelStoreOwner
    ) {
        val viewModel = koinViewModel<DemoDrawerViewModel>()
        DrawerView(destinationInfo, viewModel)
    }
}
