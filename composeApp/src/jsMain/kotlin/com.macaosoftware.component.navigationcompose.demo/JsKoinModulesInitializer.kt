package com.macaosoftware.component.navigationcompose.demo

import com.macaosoftware.component.navigationcompose.demo.startup.initializers.CommonKoinModulesInitializer
import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.plugin.account.AccountPluginEmpty
import org.koin.core.module.Module
import org.koin.dsl.module

class JsKoinModulesInitializer : CommonKoinModulesInitializer() {

    override suspend fun platformKoinModules(): List<Module> {
        // val database = createDatabase(BrowserDriverFactory())

        val jsKoinModule = module {
            // single<ITimeProvider> { DefaultTimeProvider() }
            // single<Database> { database }
            single<AccountPlugin> { AccountPluginEmpty() }
        }

        return listOf(jsKoinModule)
    }
}
