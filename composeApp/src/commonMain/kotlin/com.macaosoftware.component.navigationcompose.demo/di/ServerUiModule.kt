package com.macaosoftware.component.navigationcompose.demo.di

import com.macaosoftware.component.core.DestinationRendersRegistry
import com.macaosoftware.component.navigationcompose.demo.marketplace.DemoDestinationRendersRegistry
import com.macaosoftware.component.navigationcompose.demo.serverui.domain.usecase.LoadChildrenDestinationUseCase
import org.koin.dsl.module

internal val serverUiModule = module {

    factory<LoadChildrenDestinationUseCase> { LoadChildrenDestinationUseCase() }

    single<DestinationRendersRegistry> {
        DemoDestinationRendersRegistry()
    }
}
