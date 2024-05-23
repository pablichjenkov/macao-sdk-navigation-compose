package com.macaosoftware.component.navigationcompose.demo.di

import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.SimpleScreenViewModel
import org.koin.dsl.module

internal val miscScreensViewModelsModule = module {

    // ViewModels
    factory<SimpleScreenViewModel> { params ->
        SimpleScreenViewModel(
            bgColor = params.get(),
            resultHandler = params.get()
        )
    }
}
