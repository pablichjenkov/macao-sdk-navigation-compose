package com.macaosoftware.component.navigationcompose.demo.serverui

import androidx.compose.runtime.Composable
import com.macaosoftware.component.core.DestinationRendersRegistry
import com.macaosoftware.component.core.RootDestinationRender
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

class DemoRootDestinationRender(
    rootJsonObject: JsonObject,
    private val destinationRendersRegistry: DestinationRendersRegistry
) : RootDestinationRender {

    private val rootRoute: String by lazy {
        rootJsonObject
            .getValue(ServerUiConstants.JsonKeyName.route)
            .jsonPrimitive
            .content
    }

    private val rootRenderType: String by lazy {
        rootJsonObject
            .getValue(ServerUiConstants.JsonKeyName.componentType)
            .jsonPrimitive
            .content
    }

    override fun getRoute(): String = rootRoute

    override fun getRenderType(): String = rootRenderType

    @Composable
    override fun Content() = destinationRendersRegistry
            .renderForRoot(rootRenderType)
            .Content()

}
