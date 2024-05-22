package com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer

import com.macaosoftware.component.navigationcompose.demo.serverui.domain.JsonObjectHandler
import com.macaosoftware.component.navigationcompose.demo.serverui.domain.ServerJsonObjectManager
import com.macaosoftware.component.navigationcompose.demo.serverui.domain.ServerUiNavItemMapper

internal class DrawerSduiHandler(
    private val serverJsonObjectManager: ServerJsonObjectManager,
    private val jsonToComponentTypeMapper: ServerUiNavItemMapper
) : JsonObjectHandler(serverJsonObjectManager, jsonToComponentTypeMapper)
