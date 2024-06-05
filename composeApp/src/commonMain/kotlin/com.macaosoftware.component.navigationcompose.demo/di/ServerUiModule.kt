package com.macaosoftware.component.navigationcompose.demo.di

import com.macaosoftware.component.core.DestinationRendersRegistry
import com.macaosoftware.component.core.MacaoDestinationRendersRegistry
import com.macaosoftware.component.navigationcompose.demo.marketplace.notfound.DestinationRenderNotFound
import com.macaosoftware.component.navigationcompose.demo.marketplace.notfound.RootDestinationRenderNotFound
import com.macaosoftware.component.navigationcompose.demo.serverui.domain.usecase.LoadChildrenDestinationUseCase
import org.koin.dsl.module

internal val serverUiModule = module {

    factory<LoadChildrenDestinationUseCase> { LoadChildrenDestinationUseCase() }

    single<DestinationRendersRegistry> {
        MacaoDestinationRendersRegistry(
            destinationRenderNotFound = DestinationRenderNotFound(),
            rootDestinationRenderNotFound = RootDestinationRenderNotFound(),
        )
    }
}
