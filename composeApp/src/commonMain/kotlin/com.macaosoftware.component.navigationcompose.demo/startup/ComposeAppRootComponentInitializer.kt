package com.macaosoftware.component.navigationcompose.demo.startup

import com.macaosoftware.app.RootComponentInitializer
import com.macaosoftware.component.ComposableStateMapper
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiRemoteService
import com.macaosoftware.component.navigationcompose.demo.serverui.domain.ServerUiJsonToComponentTypeMapper
import com.macaosoftware.util.MacaoResult
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class ComposeAppRootComponentInitializer : RootComponentInitializer {

    override fun shouldShowLoader(): Boolean {
        return true
    }

    override suspend fun initialize(koinComponent: KoinComponent): MacaoResult<ComposableStateMapper> {

        val serverUiRemoteService = koinComponent.get<ServerUiRemoteService>()

        val jsonToComponentTypeMapper = ServerUiJsonToComponentTypeMapper(/*koinComponent*/)
        val rootComponentJsonResilience = serverUiRemoteService.getRootJsonResilience()
        //val rootComponentJson = sduiRemoteService.getRemoteRootComponent("123")

        // val rootComponent = jsonToComponentTypeMapper.getComponentInstanceOf(
            // componentJson = rootComponentJson ?: rootComponentJsonResilience
            // componentJson = rootComponentJsonResilience
        // )

        // Migration

        val stateMapper = RootComposableStateMapper(
            rootComponentJsonResilience
        )

        return MacaoResult.Success(stateMapper)
    }
}
