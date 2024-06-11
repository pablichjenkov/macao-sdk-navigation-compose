package com.macaosoftware.app.startup.task

import com.macaosoftware.util.MacaoResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent

class StartupTaskRunnerDefault(
    val startupTasks: List<StartupTask>
) : StartupTaskRunner {

    override fun initialize(
        koinComponent: KoinComponent
    ): Flow<StartupTasksEvents> = flow {

        startupTasks.forEach { startupTask ->

            if (startupTask.shouldShowLoader()) {
                emit(StartupTasksEvents.TaskRunning(startupTask))
            }

            val taskResult = startupTask.initialize(koinComponent)
            if (taskResult is MacaoResult.Error) {
                emit(
                    StartupTasksEvents.TaskFinishedWithError(
                        startupTask,
                        taskResult.error
                    )
                )
                // Exit the flow
                return@flow
            }
        }

        emit(StartupTasksEvents.AllTaskCompletedSuccess)
    }
}
