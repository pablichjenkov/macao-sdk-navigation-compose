package com.macaosoftware.component.navigationcompose.demo

import com.macaosoftware.app.RootKoinModuleInitializer
import com.macaosoftware.component.navigationcompose.demo.di.commonKoinModule
import com.macaosoftware.component.navigationcompose.demo.di.macaoViewModelsModule
import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.plugin.account.AccountPluginEmpty
import org.koin.core.module.Module
import org.koin.dsl.module

class JvmRootKoinModuleInitializer : RootKoinModuleInitializer {

    override suspend fun initialize(): List<Module> {

        // val database = createDatabase(DesktopDriverFactory())

        val JvmKoinModule = module {
            // single<Database> { database }
            single<AccountPlugin> { AccountPluginEmpty() }
            // single<AccountPlugin> { SupabaseAccountPlugin() }
        }

        return listOf(JvmKoinModule, commonKoinModule, macaoViewModelsModule)
    }
}
