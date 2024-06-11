package com.macaosoftware.component.navigationcompose.demo.startup.tasks

import com.macaosoftware.app.startup.task.StartupTask
import com.macaosoftware.util.MacaoResult
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent

class DatabaseMigrationStartupTask : StartupTask {

    override fun name(): String {
        return "Database Migration"
    }

    override fun shouldShowLoader(): Boolean {
        return true
    }

    override suspend fun initialize(koinComponent: KoinComponent): MacaoResult<Unit> {
        // todo: Remove this delay
        delay(800)
        return MacaoResult.Success(Unit)
    }
}