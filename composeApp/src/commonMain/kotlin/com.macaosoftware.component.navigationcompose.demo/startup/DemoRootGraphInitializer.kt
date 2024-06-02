package com.macaosoftware.component.navigationcompose.demo.startup

import com.macaosoftware.app.startup.initializers.RootGraphInitializer
import com.macaosoftware.component.core.DestinationRendersRegistry
import com.macaosoftware.component.core.RootDestinationRender
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.SimpleScreen1DestinationRender
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen.SimpleScreenDestinationRender
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.DemoDrawerRootDestinationRender
import com.macaosoftware.component.navigationcompose.demo.serverui.DemoRootDestinationRender
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiRemoteService
import com.macaosoftware.component.navigationcompose.demo.serverui.domain.ServerUiDestinationInfoParser
import com.macaosoftware.util.MacaoResult
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class DemoRootGraphInitializer : RootGraphInitializer {

    override fun shouldShowLoader(): Boolean {
        return true
    }

    override suspend fun initialize(koinComponent: KoinComponent): MacaoResult<RootDestinationRender> {

        val serverUiRemoteService = koinComponent.get<ServerUiRemoteService>()

        val jsonToComponentTypeMapper = ServerUiDestinationInfoParser(/*koinComponent*/)
        val rootComponentJsonResilience = serverUiRemoteService.getRootJsonResilience()
        //val rootComponentJson = sduiRemoteService.getRemoteRootComponent("123")

        // val rootComponent = jsonToComponentTypeMapper.getComponentInstanceOf(
        // componentJson = rootComponentJson ?: rootComponentJsonResilience
        // componentJson = rootComponentJsonResilience
        // )

        // Migration

        val destinationRendersRegistry = koinComponent
            .get<DestinationRendersRegistry>()
            .apply {
                addRoot(DemoDrawerRootDestinationRender())
                add(SimpleScreenDestinationRender())
                add(SimpleScreen1DestinationRender())
            }

        val rootDestinationRender = DemoRootDestinationRender(
            rootComponentJsonResilience,
            destinationRendersRegistry
        )

        return MacaoResult.Success(rootDestinationRender)
    }
}
