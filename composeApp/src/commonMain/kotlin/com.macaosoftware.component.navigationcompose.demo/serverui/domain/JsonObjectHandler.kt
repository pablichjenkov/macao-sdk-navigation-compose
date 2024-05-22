package com.macaosoftware.component.navigationcompose.demo.serverui.domain

import com.macaosoftware.component.core.NavItem
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject

internal abstract class JsonObjectHandler(
    private val serverJsonObjectManager: ServerJsonObjectManager,
    private val jsonToComponentTypeMapper: ServerUiNavItemMapper = ServerUiNavItemMapper()
) {
    /*suspend fun loadChildren(): List<Component> {
        val children = jsonObject.get(
            ServerUiConstants.JsonKeyName.children
        ) as JsonArray

        return children.map {
            jsonToComponentTypeMapper.getComponentInstanceOf((it as JsonObject))
        }
    }*/

    suspend fun loadNavItems(): List<NavItem> {
        val children = serverJsonObjectManager.getJson().get(
            ServerUiConstants.JsonKeyName.children
        ) as JsonArray

        return children.map {
            jsonToComponentTypeMapper.getNavItemOf((it as JsonObject))
        }
    }
}
