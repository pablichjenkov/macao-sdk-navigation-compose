package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.di

import com.macaosoftware.component.core.DestinationRender
import com.macaosoftware.component.drawer.DrawerResultAdapter
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.SimpleScreen1ToDrawerResultAdapter
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.ui.SimpleScreen1DestinationRender
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.ui.SimpleScreen1ViewModel
import org.koin.dsl.bind
import org.koin.dsl.module

internal val simpleScreen1Module = module {

    // SimpleScreen1ViewModel
    factory<SimpleScreen1ViewModel> { params ->
        SimpleScreen1ViewModel(
            bgColor = params.get()
        )
    }

    // SimpleScreen1ToDrawerResultAdapter
    factory<SimpleScreen1ToDrawerResultAdapter> { params ->
        SimpleScreen1ToDrawerResultAdapter()
    } bind (DrawerResultAdapter::class)

    // SimpleScreen1DestinationRender
    factory { SimpleScreen1DestinationRender() } bind (DestinationRender::class)
}
