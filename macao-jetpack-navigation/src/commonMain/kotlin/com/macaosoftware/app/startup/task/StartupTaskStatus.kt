package com.macaosoftware.app.startup.task

sealed class StartupTasksEvents {
    class TaskRunning(val task: StartupTask) : StartupTasksEvents()
    class TaskFinishedWithError(val task: StartupTask, val error: StartupTaskError) : StartupTasksEvents()
    object AllTaskCompletedSuccess : StartupTasksEvents()
}
