package com.macaosoftware.component.navigationcompose.demo.startup.tasks

import com.macaosoftware.app.startup.task.StartupTask
import com.macaosoftware.app.startup.task.StartupTaskError
import com.macaosoftware.util.MacaoResult
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent

class LaunchDarklyStartupTask : StartupTask {
    override fun name(): String {
        return "LaunchDarkly remote syncing ..."
    }

    override fun shouldShowLoader(): Boolean {
        return true
    }

    override suspend fun initialize(koinComponent: KoinComponent): MacaoResult<Unit, StartupTaskError> {
        // todo: Remove this delay
        delay(800)
        return MacaoResult.Success(Unit)
        //return MacaoResult.Error(MacaoError("LaunchDarklyStartupTask", "Something went wrong while fetching LaunchDarklyStartupTask, try again later"))
    }
}
