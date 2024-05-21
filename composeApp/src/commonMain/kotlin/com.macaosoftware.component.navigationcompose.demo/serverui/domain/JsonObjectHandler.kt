package com.macaosoftware.component.navigationcompose.demo.serverui.domain

import com.macaosoftware.component.core.NavItem
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject

abstract class JsonObjectHandler(
    private val jsonObject: JsonObject,
    private val jsonToComponentTypeMapper: ServerUiJsonToComponentTypeMapper
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
        val children = jsonObject.get(
            ServerUiConstants.JsonKeyName.children
        ) as JsonArray

        return children.map {
            jsonToComponentTypeMapper.getNavItemOf((it as JsonObject))
        }
    }
}
