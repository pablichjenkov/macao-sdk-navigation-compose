package com.macaosoftware.component.navigationcompose.demo.di

import com.macaosoftware.component.drawer.DrawerComponentDefaults
import com.macaosoftware.component.drawer.DrawerStatePresenterDefault
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.SimpleScreenViewModel
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.DrawerSduiHandler
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.DrawerViewModelDefault
import com.macaosoftware.component.navigationcompose.demo.serverui.domain.ServerUiNavItemMapper
import com.macaosoftware.component.navigationcompose.demo.serverui.domain.ServerJsonObjectManager
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val macaoViewModelsModule = module {

    factoryOf(::ServerJsonObjectManager)
    factoryOf(::ServerUiNavItemMapper)
    factoryOf(::DrawerSduiHandler)
    factory <DrawerStatePresenterDefault> {
        DrawerComponentDefaults.createDrawerStatePresenter()
    }
    factoryOf(::DrawerViewModelDefault)
    factory<SimpleScreenViewModel> { params ->
        SimpleScreenViewModel(
            bgColor = params.get(),
            resultHandler = params.get()
        )
    }
}
