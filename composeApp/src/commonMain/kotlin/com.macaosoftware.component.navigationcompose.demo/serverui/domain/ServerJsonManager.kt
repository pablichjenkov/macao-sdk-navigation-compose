package com.macaosoftware.component.navigationcompose.demo.serverui.domain

import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiRemoteService
import kotlinx.serialization.json.JsonObject

internal class ServerJsonManager(
    private val serverUiRemoteService: ServerUiRemoteService
) {

    suspend fun getJson(sourceId: String): JsonObject {
//        return serverUiRemoteService.getRemoteRootComponent("123")
//            ?: serverUiRemoteService.getRootJsonResilience()
        return serverUiRemoteService.getRootJsonResilience()
    }
}
