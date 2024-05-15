package com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.panel

import com.macaosoftware.component.navigationcompose.demo.serverui.domain.JsonObjectHandler
import com.macaosoftware.component.navigationcompose.demo.serverui.domain.SduiComponentFactory
import kotlinx.serialization.json.JsonObject

class PanelSduiHandler(
    private val jsonObject: JsonObject,
    private val sduiComponentFactory: SduiComponentFactory
) : JsonObjectHandler(jsonObject, sduiComponentFactory)
