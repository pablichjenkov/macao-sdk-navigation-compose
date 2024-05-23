package com.macaosoftware.component.navigationcompose.demo

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.ComposeUIViewController
import com.macaosoftware.app.MacaoApplication
import com.macaosoftware.app.MacaoApplicationState
import com.macaosoftware.app.startup.task.StartupTaskRunnerDefault
import com.macaosoftware.component.navigationcompose.demo.startup.ComposeAppDestinationsInitializer
import com.macaosoftware.component.navigationcompose.demo.startup.DatabaseMigrationStartupTask
import com.macaosoftware.component.navigationcompose.demo.startup.LaunchDarklyStartupTask
import com.macaosoftware.component.navigationcompose.demo.startup.SdkXyzStartupTask

fun ComposeViewController(
    iosBridge: IosBridge
) = ComposeUIViewController {

    val startupTasks = listOf(
        DatabaseMigrationStartupTask(),
        LaunchDarklyStartupTask(),
        SdkXyzStartupTask()
    )
    val applicationState = MacaoApplicationState(
        rootKoinModuleInitializer = IosKoinModuleInitializer(iosBridge),
        startupTaskRunner = StartupTaskRunnerDefault(startupTasks),
        destinationsInitializer = ComposeAppDestinationsInitializer()
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