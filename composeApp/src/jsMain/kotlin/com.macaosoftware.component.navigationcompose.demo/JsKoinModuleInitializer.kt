package com.macaosoftware.component.navigationcompose.demo

import com.macaosoftware.app.RootKoinModuleInitializer
import com.macaosoftware.component.navigationcompose.demo.di.commonKoinModule
import com.macaosoftware.component.navigationcompose.demo.di.macaoViewModelsModule
import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.plugin.account.AccountPluginEmpty
import org.koin.core.module.Module
import org.koin.dsl.module

class JsKoinModuleInitializer : RootKoinModuleInitializer {

    override suspend fun initialize(): List<Module> {

        // val database = createDatabase(BrowserDriverFactory())

        val JsKoinModule = module {
            // single<ITimeProvider> { DefaultTimeProvider() }
            // single<Database> { database }
            single<AccountPlugin> { AccountPluginEmpty() }
        }

        return listOf(JsKoinModule, commonKoinModule, macaoViewModelsModule)
    }
}
