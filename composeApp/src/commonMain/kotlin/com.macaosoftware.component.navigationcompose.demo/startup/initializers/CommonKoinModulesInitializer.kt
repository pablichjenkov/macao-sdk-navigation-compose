package com.macaosoftware.component.navigationcompose.demo.startup.initializers

import com.macaosoftware.app.startup.initializers.KoinModulesInitializer
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen.di.simpleScreenModule
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.di.simpleScreen1Module
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.di.drawerModule
import com.macaosoftware.component.navigationcompose.demo.marketplace.notfound.di.destinationNotFoundModule
import com.macaosoftware.component.navigationcompose.demo.serverui.di.serverUiModule
import com.macaosoftware.component.navigationcompose.demo.system.di.commonKoinModule
import com.macaosoftware.plugin.CoroutineDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.koin.core.module.Module

abstract class CommonKoinModulesInitializer(
    private val ioDispatcher: CoroutineDispatcher = CoroutineDispatchers.Default.io
) : KoinModulesInitializer {

    override suspend fun initialize(): List<Module> = withContext(ioDispatcher) {
        commonKoinModules() + platformKoinModules()
    }

    private suspend fun commonKoinModules(): List<Module> = mutableListOf<Module>().apply {

        // add(defaultModule)
        // add(CommonModule().module)

        // Common Utils like HttpClient, Date classes ...
        add(commonKoinModule)

        // Module containing the Server Driven UI business logic
        add(serverUiModule)

        // Drawer module
        add(drawerModule)

        // Miscellaneous screens module
        add(simpleScreenModule)
        add(simpleScreen1Module)
        add(destinationNotFoundModule)
    }

    abstract suspend fun platformKoinModules(): List<Module>

}
