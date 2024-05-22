package com.macaosoftware.component.navigationcompose.demo.serverui.domain

import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiRemoteService
import kotlinx.serialization.json.JsonObject

internal class ServerJsonObjectManager(
    private val serverUiRemoteService: ServerUiRemoteService
) {

    fun getJson(): JsonObject {
        return serverUiRemoteService.getRootJsonResilience()
    }

}
