package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen.di

import com.macaosoftware.component.core.DestinationRender
import com.macaosoftware.component.drawer.DrawerResultAdapter
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen.SimpleScreenToDrawerResultAdapter
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

    // SimpleScreenToDrawerResultAdapter
    factory<SimpleScreenToDrawerResultAdapter> { params ->
        SimpleScreenToDrawerResultAdapter()
    } bind (DrawerResultAdapter::class)

    // SimpleScreenDestinationRender
    factory { SimpleScreenDestinationRender() } bind (DestinationRender::class)
}
