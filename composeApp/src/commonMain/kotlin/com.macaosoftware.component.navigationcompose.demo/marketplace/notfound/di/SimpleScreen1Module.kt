package com.macaosoftware.component.navigationcompose.demo.marketplace.notfound.di

import com.macaosoftware.component.drawer.DrawerResultAdapter
import com.macaosoftware.component.navigationcompose.demo.marketplace.notfound.result.NotFoundToDrawerResultAdapter
import org.koin.dsl.bind
import org.koin.dsl.module

internal val destinationNotFoundModule = module {

    // NotFoundToDrawerResultAdapter
    factory<NotFoundToDrawerResultAdapter> {
        NotFoundToDrawerResultAdapter()
    } bind (DrawerResultAdapter::class)

}
