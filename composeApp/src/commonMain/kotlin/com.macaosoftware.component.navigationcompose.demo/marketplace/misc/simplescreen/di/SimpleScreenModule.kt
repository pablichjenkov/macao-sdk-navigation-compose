package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen.di

import com.macaosoftware.component.core.DestinationRender
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen.ui.SimpleScreenDestinationRender
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen.ui.SimpleScreenViewModel
import org.koin.dsl.bind
import org.koin.dsl.module

internal val simpleScreenModule = module {

    // SimpleScreenViewModel
    factory<SimpleScreenViewModel> { params ->
        SimpleScreenViewModel(
            bgColor = params.get()
        )
    }

    // SimpleScreenDestinationRender
    factory { SimpleScreenDestinationRender() } bind (DestinationRender::class)
}
