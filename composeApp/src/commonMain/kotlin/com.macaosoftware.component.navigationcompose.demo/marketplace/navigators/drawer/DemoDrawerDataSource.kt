package com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer

import com.macaosoftware.component.navigationcompose.demo.serverui.domain.ServerJsonManager
import com.macaosoftware.component.navigationcompose.demo.serverui.domain.ServerUiDataSource
import com.macaosoftware.component.navigationcompose.demo.serverui.domain.ServerUiDestinationInfoParser

internal class DemoDrawerDataSource(
    private val serverJsonManager: ServerJsonManager,
    private val jsonToComponentTypeMapper: ServerUiDestinationInfoParser
) : ServerUiDataSource(serverJsonManager, jsonToComponentTypeMapper)
