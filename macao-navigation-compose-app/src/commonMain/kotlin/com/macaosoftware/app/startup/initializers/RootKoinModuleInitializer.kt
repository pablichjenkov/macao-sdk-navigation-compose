package com.macaosoftware.app.startup.initializers

import org.koin.core.module.Module

interface RootKoinModuleInitializer {
    suspend fun initialize() : List<Module>
}