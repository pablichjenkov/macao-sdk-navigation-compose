package com.macaosoftware.app.startup.task

import com.macaosoftware.util.MacaoError

sealed class StartupTasksEvents {
    class TaskRunning(val task: StartupTask) : StartupTasksEvents()
    class TaskFinishedWithError(val task: StartupTask, val error: MacaoError) : StartupTasksEvents()
    object AllTaskCompletedSuccess : StartupTasksEvents()
}
