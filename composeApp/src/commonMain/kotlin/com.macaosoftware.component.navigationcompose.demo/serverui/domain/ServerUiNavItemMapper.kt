package com.macaosoftware.component.navigationcompose.demo.serverui.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.DateRange
import androidx.compose.material.icons.sharp.List
import com.macaosoftware.component.core.NavItem
import com.macaosoftware.component.navigationcompose.demo.serverui.MiscScreensDestinationPresenter
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

class ServerUiNavItemMapper {

    fun getNavItemOf(
        componentJson: JsonObject
    ): NavItem {
        val childComponentType: String =
            componentJson
                .getValue(ServerUiConstants.JsonKeyName.componentType)
                .jsonPrimitive
                .content

        return when (childComponentType) {

            ServerUiConstants.ComponentType.SimpleScreen -> {
                NavItem(
                    destinationPresenter = MiscScreensDestinationPresenter(),
                    label = ServerUiConstants.ComponentType.SimpleScreen,
                    icon = Icons.Sharp.DateRange
                )
            }

            ServerUiConstants.ComponentType.SimpleScreen1 -> {
                NavItem(
                    destinationPresenter = MiscScreensDestinationPresenter(),
                    label = ServerUiConstants.ComponentType.SimpleScreen1,
                    icon = Icons.Sharp.List
                )
            }

            else -> {
                throw IllegalArgumentException("Missing NavItem factory for $childComponentType")
            }
        }
    }

}
