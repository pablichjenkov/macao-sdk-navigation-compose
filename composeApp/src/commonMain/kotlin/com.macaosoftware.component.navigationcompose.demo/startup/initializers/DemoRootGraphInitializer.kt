package com.macaosoftware.component.navigationcompose.demo.startup.initializers

import com.macaosoftware.app.startup.initializers.RootGraphInitializer
import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.core.DestinationRendersRegistry
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen.SimpleScreenDestinationRender
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.SimpleScreen1DestinationRender
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.DemoDrawerRootDestinationRender
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiRemoteService
import com.macaosoftware.util.MacaoResult
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class DemoRootGraphInitializer : RootGraphInitializer {

    override fun shouldShowLoader(): Boolean {
        return true
    }

    override suspend fun initialize(koinComponent: KoinComponent): MacaoResult<DestinationInfo> {

        val serverUiRemoteService = koinComponent.get<ServerUiRemoteService>()

        // val rootDestinationJson = serverUiRemoteService.getRootJsonResilience()

        // val rootDestinationJson = serverUiRemoteService.getRemoteRootComponent("123")
        //    ?: serverUiRemoteService.getRootJsonResilience()

        // Migration

        val rootDestinationInfo = serverUiRemoteService.getRootDestinationInfo()

        // TODO: This can be done using koin.getAll()
        val destinationRendersRegistry = koinComponent
            .get<DestinationRendersRegistry>()
            .apply {
                addRoot(DemoDrawerRootDestinationRender())
                add(SimpleScreenDestinationRender())
                add(SimpleScreen1DestinationRender())
            }

        return MacaoResult.Success(rootDestinationInfo)
    }
}
