package com.macaosoftware.component.navigationcompose.demo

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.CanvasBasedWindow
import com.macaosoftware.app.MacaoApplication
import com.macaosoftware.app.MacaoApplicationState
import com.macaosoftware.app.startup.task.StartupTaskRunnerDefault
import com.macaosoftware.component.navigationcompose.demo.startup.ComposeAppDestinationsInitializer
import com.macaosoftware.component.navigationcompose.demo.startup.DatabaseMigrationStartupTask
import com.macaosoftware.component.navigationcompose.demo.startup.LaunchDarklyStartupTask
import com.macaosoftware.component.navigationcompose.demo.startup.SdkXyzStartupTask
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {

        val startupTasks = listOf(
            DatabaseMigrationStartupTask(),
            LaunchDarklyStartupTask(),
            SdkXyzStartupTask()
        )

        val applicationState = MacaoApplicationState(
            rootKoinModuleInitializer = JsKoinModuleInitializer(),
            startupTaskRunner = StartupTaskRunnerDefault(startupTasks),
            destinationsInitializer = ComposeAppDestinationsInitializer()
        )

        CanvasBasedWindow("Macao Navigation-Compose JS Demo") {
            MaterialTheme {
                MacaoApplication(applicationState = applicationState)
            }
        }
    }
}