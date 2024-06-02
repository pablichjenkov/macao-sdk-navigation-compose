package com.macaosoftware.component.navigationcompose.demo.serverui.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.AccountBox
import androidx.compose.material.icons.sharp.DateRange
import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

class ServerUiDestinationInfoParser {

    // TODO: Define the right schema for the destination in the server ktor application and parse
    //  the full Destination object.
    fun mapDestinationFromJson(
        componentJson: JsonObject
    ): DestinationInfo {

        val childComponentType: String =
            componentJson
                .getValue(ServerUiConstants.JsonKeyName.componentType)
                .jsonPrimitive
                .content

        return when (childComponentType) {

            ServerUiConstants.ComponentType.SimpleScreen -> {
                DestinationInfo(
                    route = ServerUiConstants.Routes.RootGraph.SimpleScreen,
                    renderType = ServerUiConstants.ComponentType.SimpleScreen,
                    dataSource = "http://macao-software/custumer/123/destination/root.simple.0",
                    label = "Label: " + ServerUiConstants.ComponentType.SimpleScreen,
                    icon = Icons.Sharp.DateRange
                )
            }

            ServerUiConstants.ComponentType.SimpleScreen1 -> {
                DestinationInfo(
                    route = ServerUiConstants.Routes.RootGraph.SimpleScreen1,
                    renderType = ServerUiConstants.ComponentType.SimpleScreen1,
                    dataSource = "http://macao-software/custumer/123/destination/root.simple.1",
                    label = "Label: " + ServerUiConstants.ComponentType.SimpleScreen1,
                    icon = Icons.Sharp.AccountBox
                )
            }

            else -> {
                DestinationInfo(
                    route = ServerUiConstants.Routes.GLOBAL_SCREEN_404,
                    renderType = ServerUiConstants.ComponentType.DestinationNotFound,
                    dataSource = "",
                    label = "Label: " + ServerUiConstants.ComponentType.DestinationNotFound,
                    icon = Icons.Sharp.DateRange
                )
            }
        }
    }

}
