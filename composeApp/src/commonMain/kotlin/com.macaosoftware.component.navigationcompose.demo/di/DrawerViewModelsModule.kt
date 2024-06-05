package com.macaosoftware.component.navigationcompose.demo.di

import com.macaosoftware.component.drawer.DrawerComponentDefaults
import com.macaosoftware.component.drawer.DrawerStatePresenterDefault
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.DemoDrawerDataSource
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.DemoDrawerViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.parameter.ParametersHolder
import org.koin.dsl.module

internal val drawerViewModelsModule = module {

    factoryOf(::DemoDrawerDataSource)
    factory<DrawerStatePresenterDefault> {
        DrawerComponentDefaults.createDrawerStatePresenter()
    }

    // ViewModels
    viewModelOf(::DemoDrawerViewModel)
    /*viewModel<DemoDrawerViewModel> { parameters: ParametersHolder ->
        DemoDrawerViewModel(
            route = parameters.get(),
            drawerDataSource = get(),
            drawerStatePresenter = get(),
            destinationRendersRegistry = get()
        )
    }*/
}
