package com.macaosoftware.component.navigationcompose.demo.di

import com.macaosoftware.component.drawer.DrawerComponentDefaults
import com.macaosoftware.component.drawer.DrawerStatePresenterDefault
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.DrawerSduiHandler
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.DrawerViewModelDefault
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val drawerViewModelsModule = module {

    factoryOf(::DrawerSduiHandler)
    factory<DrawerStatePresenterDefault> {
        DrawerComponentDefaults.createDrawerStatePresenter()
    }

    // ViewModels
    viewModelOf(::DrawerViewModelDefault)
}
