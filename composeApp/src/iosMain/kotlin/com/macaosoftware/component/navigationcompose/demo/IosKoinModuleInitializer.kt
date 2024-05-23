package com.macaosoftware.component.navigationcompose.demo

import com.macaosoftware.app.startup.initializers.RootKoinModuleInitializer
import com.macaosoftware.component.navigationcompose.demo.di.commonKoinModule
import com.macaosoftware.component.navigationcompose.demo.di.drawerViewModelsModule
import com.macaosoftware.component.navigationcompose.demo.di.miscScreensViewModelsModule
import com.macaosoftware.component.navigationcompose.demo.di.serverUiModule
import org.koin.core.module.Module
import org.koin.dsl.module

class IosKoinModuleInitializer(
    private val iosBridge: IosBridge
) : RootKoinModuleInitializer {

    override suspend fun initialize(): List<Module> {

        // val database = createDatabase(IosDriverFactory())

        val iOSKoinModule = module {
            // single<Database> { database }
            // single<AccountPlugin> { iosBridge.accountPlugin }
        }

        return listOf(
            iOSKoinModule,
            commonKoinModule,
            serverUiModule,
            drawerViewModelsModule,
            miscScreensViewModelsModule
        )
    }
}
