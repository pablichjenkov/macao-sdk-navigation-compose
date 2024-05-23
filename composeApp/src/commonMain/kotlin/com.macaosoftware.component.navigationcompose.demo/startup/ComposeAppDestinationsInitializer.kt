package com.macaosoftware.component.navigationcompose.demo.startup

import com.macaosoftware.app.startup.initializers.DestinationsInitializer
import com.macaosoftware.component.core.RootDestinationPresenter
import com.macaosoftware.component.navigationcompose.demo.serverui.ComposeAppRootDestinationPresenter
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiRemoteService
import com.macaosoftware.component.navigationcompose.demo.serverui.domain.ServerUiNavItemMapper
import com.macaosoftware.util.MacaoResult
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class ComposeAppDestinationsInitializer : DestinationsInitializer {

    override fun shouldShowLoader(): Boolean {
        return true
    }

    override suspend fun initialize(koinComponent: KoinComponent): MacaoResult<RootDestinationPresenter> {

        val serverUiRemoteService = koinComponent.get<ServerUiRemoteService>()

        val jsonToComponentTypeMapper = ServerUiNavItemMapper(/*koinComponent*/)
        val rootComponentJsonResilience = serverUiRemoteService.getRootJsonResilience()
        //val rootComponentJson = sduiRemoteService.getRemoteRootComponent("123")

        // val rootComponent = jsonToComponentTypeMapper.getComponentInstanceOf(
            // componentJson = rootComponentJson ?: rootComponentJsonResilience
            // componentJson = rootComponentJsonResilience
        // )

        // Migration

        val rootDestinationRender = ComposeAppRootDestinationPresenter(
            rootComponentJsonResilience
        )

        return MacaoResult.Success(rootDestinationRender)
    }
}
