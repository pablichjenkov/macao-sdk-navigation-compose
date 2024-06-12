package com.macaosoftware.component.navigationcompose.demo.marketplace.entrypoints

import androidx.compose.runtime.Composable
import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.core.RootDestinationRender
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
import com.macaosoftware.component.stack.StackView
import org.koin.compose.viewmodel.koinViewModel

class AppCoordinatorV2RootDestinationRender : RootDestinationRender {

    override fun getRenderType(): String {
        return ServerUiConstants.ComponentType.AppCoordinatorV2
    }

    @Composable
    override fun Content(destinationInfo: DestinationInfo) {
        val viewModel = koinViewModel<AppCoordinatorV2ViewModel>()
        StackView(destinationInfo, viewModel)
    }
}