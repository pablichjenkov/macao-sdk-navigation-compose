package com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.AccountBox
import androidx.compose.material.icons.sharp.DateRange
import androidx.core.bundle.Bundle
import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants

class DemoDrawerStateLoaderUseCase {

    suspend fun loadChildrenDestinations(dataSourceUrl: String): List<DestinationInfo> {

        return listOf(
            DestinationInfo(
                route = ServerUiConstants.Routes.RootGraph.SimpleScreen,
                renderType = ServerUiConstants.ComponentType.SimpleScreen,
                dataSource = "http://macao-software/custumer/123/destination/root.simple.0",
                label = "Label: " + ServerUiConstants.ComponentType.SimpleScreen,
                icon = Icons.Sharp.DateRange
            ),
            DestinationInfo(
                route = ServerUiConstants.Routes.RootGraph.SimpleScreen1,
                renderType = ServerUiConstants.ComponentType.SimpleScreen1,
                dataSource = "http://macao-software/custumer/123/destination/root.simple.1",
                label = "Label: " + ServerUiConstants.ComponentType.SimpleScreen1,
                icon = Icons.Sharp.AccountBox,
                props = Bundle().apply { putLong("BackgroundColor", 0xFFAA112b) }
            ),
            DestinationInfo(
                route = ServerUiConstants.Routes.GLOBAL_SCREEN_404,
                renderType = ServerUiConstants.ComponentType.DestinationNotFound,
                dataSource = "",
                label = "Label: " + ServerUiConstants.ComponentType.DestinationNotFound,
                icon = Icons.Sharp.DateRange
            )
        )
    }

}
