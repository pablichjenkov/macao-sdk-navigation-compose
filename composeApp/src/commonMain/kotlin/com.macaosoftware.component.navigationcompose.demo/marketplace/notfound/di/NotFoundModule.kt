package com.macaosoftware.component.navigationcompose.demo.marketplace.notfound.di

import com.macaosoftware.component.core.DestinationRender
import com.macaosoftware.component.drawer.DrawerResultProcessor
import com.macaosoftware.component.navigationcompose.demo.marketplace.notfound.DestinationRenderNotFound
import com.macaosoftware.component.navigationcompose.demo.marketplace.notfound.result.NotFoundToDrawerResultProcessor
import org.koin.dsl.bind
import org.koin.dsl.module

internal val notFoundModule = module {

    // SimpleScreen1ToDrawerResultAdapter
    factory<NotFoundToDrawerResultProcessor> {
        NotFoundToDrawerResultProcessor()
    } bind (DrawerResultProcessor::class)

    // SimpleScreen1DestinationRender
    factory { DestinationRenderNotFound() } bind (DestinationRender::class)
}
