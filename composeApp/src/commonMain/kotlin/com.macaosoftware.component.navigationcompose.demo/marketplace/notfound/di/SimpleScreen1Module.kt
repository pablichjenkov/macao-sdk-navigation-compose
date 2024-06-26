package com.macaosoftware.component.navigationcompose.demo.marketplace.notfound.di

import com.macaosoftware.component.drawer.DrawerResultHandler
import com.macaosoftware.component.navigationcompose.demo.marketplace.notfound.result.NotFoundToDrawerResultHandler
import org.koin.dsl.bind
import org.koin.dsl.module

internal val destinationNotFoundModule = module {

    // NotFoundToDrawerResultAdapter
    factory<NotFoundToDrawerResultHandler> {
        NotFoundToDrawerResultHandler()
    } bind (DrawerResultHandler::class)

}
