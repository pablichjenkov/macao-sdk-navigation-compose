package com.macaosoftware.component.navigationcompose.demo

import com.macaosoftware.component.navigationcompose.demo.startup.initializers.CommonKoinModulesInitializer
import org.koin.core.module.Module
import org.koin.dsl.module

class IosKoinModulesInitializer(
    private val iosBridge: IosBridge
) : CommonKoinModulesInitializer() {

    override suspend fun platformKoinModules(): List<Module> {

        /**
         * Place all the plugins coming in the iosBridge into the
         * Koin root graph.
         * */

        // val database = createDatabase(IosDriverFactory())

        val iOSKoinModule = module {
            // single<Database> { database }
            // single<AccountPlugin> { iosBridge.accountPlugin }
        }

        return listOf(iOSKoinModule)
    }
}
