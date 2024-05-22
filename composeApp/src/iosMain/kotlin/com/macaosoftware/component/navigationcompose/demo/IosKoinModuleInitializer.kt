package com.macaosoftware.component.navigationcompose.demo

import com.macaosoftware.app.RootKoinModuleInitializer
import com.macaosoftware.component.navigationcompose.demo.di.commonKoinModule
import com.macaosoftware.component.navigationcompose.demo.di.macaoViewModelsModule
import com.macaosoftware.plugin.account.AccountPlugin
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

        return listOf(iOSKoinModule, commonKoinModule, macaoViewModelsModule)
    }
}
