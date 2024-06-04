package com.macaosoftware.component.navigationcompose.demo

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.ComposeUIViewController
import com.macaosoftware.app.MacaoApplication
import com.macaosoftware.app.MacaoApplicationState
import com.macaosoftware.app.startup.task.StartupTaskRunnerDefault
import com.macaosoftware.component.navigationcompose.demo.startup.initializers.DemoRootGraphInitializer
import com.macaosoftware.component.navigationcompose.demo.startup.tasks.DatabaseMigrationStartupTask
import com.macaosoftware.component.navigationcompose.demo.startup.tasks.LaunchDarklyStartupTask
import com.macaosoftware.component.navigationcompose.demo.startup.tasks.FirebaseConfigStartupTask

fun ComposeViewController(
    iosBridge: IosBridge
) = ComposeUIViewController {

    val startupTasks = listOf(
        DatabaseMigrationStartupTask(),
        LaunchDarklyStartupTask(),
        FirebaseConfigStartupTask()
    )
    val applicationState = MacaoApplicationState(
        rootKoinModuleInitializer = IosKoinModuleInitializer(iosBridge),
        startupTaskRunner = StartupTaskRunnerDefault(startupTasks),
        rootGraphInitializer = DemoRootGraphInitializer()
    )

    MaterialTheme {
        MacaoApplication(applicationState = applicationState)
    }
}

fun createPlatformBridge(
    // accountPlugin: AccountPlugin
): IosBridge {

    return IosBridge(
        // accountPlugin = accountPlugin
    )
}