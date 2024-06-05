package com.macaosoftware.component.navigationcompose.demo.di

import com.macaosoftware.component.core.DestinationRender
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen.SimpleScreenDestinationRender
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen.SimpleScreenViewModel
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.SimpleScreen1DestinationRender
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.SimpleScreen1ViewModel
import org.koin.dsl.bind
import org.koin.dsl.module

internal val miscScreensViewModelsModule = module {

    // SimpleScreenViewModel
    factory<SimpleScreenViewModel> { params ->
        SimpleScreenViewModel(
            bgColor = params.get(),
            resultHandler = params.get()
        )
    }

    factory<SimpleScreen1ViewModel> { params ->
        SimpleScreen1ViewModel(
            bgColor = params.get(),
            resultHandler = params.get()
        )
    }

    factory { SimpleScreenDestinationRender() } bind (DestinationRender::class)
    factory { SimpleScreen1DestinationRender() } bind (DestinationRender::class)
}
