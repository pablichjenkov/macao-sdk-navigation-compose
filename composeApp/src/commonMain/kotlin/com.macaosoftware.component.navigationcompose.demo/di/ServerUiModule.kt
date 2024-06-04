package com.macaosoftware.component.navigationcompose.demo.di

import com.macaosoftware.component.core.DestinationRendersRegistry
import com.macaosoftware.component.navigationcompose.demo.marketplace.DemoDestinationRendersRegistry
import com.macaosoftware.component.navigationcompose.demo.serverui.domain.ServerJsonManager
import com.macaosoftware.component.navigationcompose.demo.serverui.domain.ServerUiDestinationInfoParser
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val serverUiModule = module {

    single<DestinationRendersRegistry> {
        DemoDestinationRendersRegistry()
    }
    factoryOf(::ServerJsonManager)
    factoryOf(::ServerUiDestinationInfoParser)
}
