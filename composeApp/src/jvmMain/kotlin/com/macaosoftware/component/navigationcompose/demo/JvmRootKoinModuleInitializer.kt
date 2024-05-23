package com.macaosoftware.component.navigationcompose.demo

import com.macaosoftware.app.startup.initializers.RootKoinModuleInitializer
import com.macaosoftware.component.navigationcompose.demo.di.commonKoinModule
import com.macaosoftware.component.navigationcompose.demo.di.drawerViewModelsModule
import com.macaosoftware.component.navigationcompose.demo.di.serverUiModule
import com.macaosoftware.component.navigationcompose.demo.di.miscScreensViewModelsModule
import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.plugin.account.AccountPluginEmpty
import org.koin.core.module.Module
import org.koin.dsl.module

class JvmRootKoinModuleInitializer : RootKoinModuleInitializer {

    override suspend fun initialize(): List<Module> {

        // val database = createDatabase(DesktopDriverFactory())

        val jvmKoinModule = module {
            // single<Database> { database }
            single<AccountPlugin> { AccountPluginEmpty() }
            // single<AccountPlugin> { SupabaseAccountPlugin() }
        }

        return listOf(
            jvmKoinModule,
            commonKoinModule,
            serverUiModule,
            drawerViewModelsModule,
            miscScreensViewModelsModule
        )
    }
}
