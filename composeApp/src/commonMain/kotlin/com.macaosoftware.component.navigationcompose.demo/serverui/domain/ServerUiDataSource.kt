package com.macaosoftware.component.navigationcompose.demo.serverui.domain

import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject

internal abstract class ServerUiDataSource(
    private val serverJsonManager: ServerJsonManager,
    private val destinationInfoParser: ServerUiDestinationInfoParser
) {

    suspend fun loadDestinations(dataSourceUrl: String): List<DestinationInfo> {

        // TODO: Replace this with a real service call
        // Should get children destinations url and load them
        val children = serverJsonManager.getJson(dataSourceUrl).get(
            ServerUiConstants.JsonKeyName.children
        ) as JsonArray

        return children.map {
            destinationInfoParser.mapDestinationFromJson((it as JsonObject))
        }
    }
}
