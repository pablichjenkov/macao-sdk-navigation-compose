package com.macaosoftware.component.navigationcompose.demo.startup.initializers

import com.macaosoftware.app.startup.initializers.RootGraphInitializer
import com.macaosoftware.app.startup.initializers.RootGraphInitializerError
import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiRemoteService
import com.macaosoftware.util.MacaoResult
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class DemoRootGraphInitializer : RootGraphInitializer {

    override fun shouldShowLoader(): Boolean {
        return true
    }

    override suspend fun initialize(koinComponent: KoinComponent): MacaoResult<DestinationInfo, RootGraphInitializerError> {

        val serverUiRemoteService = koinComponent.get<ServerUiRemoteService>()
        val rootDestinationInfo = serverUiRemoteService.getRootDestinationInfo()

        return MacaoResult.Success(rootDestinationInfo)
    }
}
