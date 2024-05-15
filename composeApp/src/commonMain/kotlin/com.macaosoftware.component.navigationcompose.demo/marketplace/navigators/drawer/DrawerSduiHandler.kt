package com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer

import com.macaosoftware.component.navigationcompose.demo.serverui.domain.JsonObjectHandler
import com.macaosoftware.component.navigationcompose.demo.serverui.domain.SduiComponentFactory
import kotlinx.serialization.json.JsonObject

class DrawerSduiHandler(
    private val jsonObject: JsonObject,
    private val sduiComponentFactory: SduiComponentFactory
) : JsonObjectHandler(jsonObject, sduiComponentFactory)
