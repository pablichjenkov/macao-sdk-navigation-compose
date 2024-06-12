package com.macaosoftware.component.navigationcompose.demo.serverui.di

import com.macaosoftware.component.core.DestinationRendersRegistry
import com.macaosoftware.component.core.MacaoDestinationRendersRegistry
import com.macaosoftware.component.navigationcompose.demo.marketplace.notfound.NotFoundDestinationRender
import com.macaosoftware.component.navigationcompose.demo.marketplace.notfound.NotFoundRootDestinationRender
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiRemoteService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val serverUiModule = module {

    single<DestinationRendersRegistry> {
        MacaoDestinationRendersRegistry(
            destinationRenderNotFound = NotFoundDestinationRender(),
            rootDestinationRenderNotFound = NotFoundRootDestinationRender(),
        )
    }

    singleOf(::ServerUiRemoteService)
}
