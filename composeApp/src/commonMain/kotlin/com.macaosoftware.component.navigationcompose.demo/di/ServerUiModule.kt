package com.macaosoftware.component.navigationcompose.demo.di

import com.macaosoftware.component.core.DestinationPresentersRegistry
import com.macaosoftware.component.navigationcompose.demo.serverui.domain.ServerJsonObjectManager
import com.macaosoftware.component.navigationcompose.demo.serverui.domain.ServerUiNavItemMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val serverUiModule = module {

    singleOf(::DestinationPresentersRegistry)
    factoryOf(::ServerJsonObjectManager)
    factoryOf(::ServerUiNavItemMapper)
}
