package com.macaosoftware.component.navigationcompose.demo

import android.app.Activity
import com.macaosoftware.app.RootKoinModuleInitializer
import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.plugin.account.AccountPluginEmpty
import com.macaosoftware.component.navigationcompose.demo.di.commonKoinModule
import org.koin.core.module.Module
import org.koin.dsl.module

class AndroidKoinModuleInitializer(
    private val activity: Activity
) : RootKoinModuleInitializer {

    override suspend fun initialize(): List<Module> {

        //val database = createDatabase(AndroidDriverFactory(activity))

        val androidKoinModule = module {
            // single<Database> { database }
             single<AccountPlugin> { AccountPluginEmpty() }
            // single<AccountPlugin> { SupabaseAccountPlugin() }
        }

        return listOf(androidKoinModule, commonKoinModule)
    }
}
