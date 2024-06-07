package com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.di

import com.macaosoftware.component.core.RootDestinationRender
import com.macaosoftware.component.drawer.DrawerHeaderDefaultState
import com.macaosoftware.component.drawer.DrawerHeaderState
import com.macaosoftware.component.drawer.DrawerStatePresenter
import com.macaosoftware.component.drawer.DrawerStatePresenterDefault
import com.macaosoftware.component.drawer.DrawerStyle
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.domain.DemoDrawerStateLoaderUseCase
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.ui.DemoDrawerRootDestinationRender
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.ui.DemoDrawerViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.parameter.ParametersHolder
import org.koin.dsl.bind
import org.koin.dsl.module

internal val drawerModule = module {

    factory<DemoDrawerStateLoaderUseCase> { DemoDrawerStateLoaderUseCase() }

    // Drawer Presenter
    factory<DrawerStatePresenter> { parameters: ParametersHolder ->

        val drawerStyle = DrawerStyle()

        val drawerHeaderState = DrawerHeaderDefaultState(
            title = "Header Title",
            description = "This is the default text. Provide your own text for your App",
            imageUri = "",
            style = drawerStyle
        )

        DrawerStatePresenterDefault(
            dispatcher = parameters.getOrNull<CoroutineDispatcher>() ?: Dispatchers.Main,
            drawerHeaderState = parameters.getOrNull<DrawerHeaderState>() ?: drawerHeaderState,
            drawerStyle = drawerStyle
        )
    }

    // DrawerViewModel
    viewModelOf(::DemoDrawerViewModel)

    factory { DemoDrawerRootDestinationRender() } bind (RootDestinationRender::class)
}
