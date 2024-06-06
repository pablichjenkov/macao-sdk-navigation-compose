package com.macaosoftware.component.navigationcompose.demo

import android.app.Activity
import com.macaosoftware.component.navigationcompose.demo.startup.initializers.CommonKoinModulesInitializer
import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.plugin.account.AccountPluginEmpty
import org.koin.core.module.Module
import org.koin.dsl.module

class AndroidKoinModulesInitializer(
    private val activity: Activity
) : CommonKoinModulesInitializer() {

    override suspend fun platformKoinModules(): List<Module> {
        //val database = createDatabase(AndroidDriverFactory(activity))

        val androidKoinModule = module {
            // single<Database> { database }
            single<AccountPlugin> { AccountPluginEmpty() }
            // single<AccountPlugin> { SupabaseAccountPlugin() }
        }

        return listOf(androidKoinModule)
    }
}
