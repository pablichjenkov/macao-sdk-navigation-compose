package com.macaosoftware.app.startup.initializers

import org.koin.core.module.Module

interface KoinModulesInitializer {
    suspend fun initialize() : List<Module>
}