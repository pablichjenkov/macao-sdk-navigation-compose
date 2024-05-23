package com.macaosoftware.component.navigationcompose.demo

import com.macaosoftware.app.startup.initializers.RootKoinModuleInitializer
import com.macaosoftware.component.navigationcompose.demo.di.commonKoinModule
import com.macaosoftware.component.navigationcompose.demo.di.drawerViewModelsModule
import com.macaosoftware.component.navigationcompose.demo.di.miscScreensViewModelsModule
import com.macaosoftware.component.navigationcompose.demo.di.serverUiModule
import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.plugin.account.AccountPluginEmpty
import org.koin.core.module.Module
import org.koin.dsl.module

class JsKoinModuleInitializer : RootKoinModuleInitializer {

    override suspend fun initialize(): List<Module> {

        // val database = createDatabase(BrowserDriverFactory())

        val jsKoinModule = module {
            // single<ITimeProvider> { DefaultTimeProvider() }
            // single<Database> { database }
            single<AccountPlugin> { AccountPluginEmpty() }
        }

        return listOf(
            jsKoinModule,
            commonKoinModule,
            serverUiModule,
            drawerViewModelsModule,
            miscScreensViewModelsModule
        )
    }
}
