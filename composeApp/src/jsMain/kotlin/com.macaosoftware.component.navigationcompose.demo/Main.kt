package com.macaosoftware.component.navigationcompose.demo

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.CanvasBasedWindow
import com.macaosoftware.app.MacaoApplication
import com.macaosoftware.app.MacaoApplicationState
import com.macaosoftware.app.startup.task.StartupTaskRunnerDefault
import com.macaosoftware.component.navigationcompose.demo.startup.initializers.DemoRootGraphInitializer
import com.macaosoftware.component.navigationcompose.demo.startup.tasks.DatabaseMigrationStartupTask
import com.macaosoftware.component.navigationcompose.demo.startup.tasks.LaunchDarklyStartupTask
import com.macaosoftware.component.navigationcompose.demo.startup.tasks.FirebaseConfigStartupTask
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {

        val startupTasks = listOf(
            DatabaseMigrationStartupTask(),
            LaunchDarklyStartupTask(),
            FirebaseConfigStartupTask()
        )

        val applicationState = MacaoApplicationState(
            rootKoinModuleInitializer = JsKoinModuleInitializer(),
            startupTaskRunner = StartupTaskRunnerDefault(startupTasks),
            rootGraphInitializer = DemoRootGraphInitializer()
        )

        CanvasBasedWindow("Macao Navigation-Compose JS Demo") {
            MaterialTheme {
                MacaoApplication(applicationState = applicationState)
            }
        }
    }
}
