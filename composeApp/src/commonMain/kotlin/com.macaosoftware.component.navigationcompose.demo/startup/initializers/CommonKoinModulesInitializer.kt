package com.macaosoftware.component.navigationcompose.demo.startup.initializers

import com.macaosoftware.app.startup.initializers.KoinModulesInitializer
import com.macaosoftware.component.navigationcompose.demo.di.commonKoinModule
import com.macaosoftware.component.navigationcompose.demo.di.drawerViewModelsModule
import com.macaosoftware.component.navigationcompose.demo.di.miscScreensViewModelsModule
import com.macaosoftware.component.navigationcompose.demo.di.serverUiModule
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

        // Common Utils like HttpClient, Date classes ...
        add(commonKoinModule)

        // Module containing the Server Driven UI business logic
        add(serverUiModule)

        // Drawer module
        add(drawerViewModelsModule)

        // Miscellaneous screens module
        add(miscScreensViewModelsModule)
    }

    abstract suspend fun platformKoinModules(): List<Module>

}
