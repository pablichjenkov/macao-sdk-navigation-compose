package com.macaosoftware.component.navigationcompose.demo.di

import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.SimpleScreen1ViewModel
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen.SimpleScreenViewModel
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
}
