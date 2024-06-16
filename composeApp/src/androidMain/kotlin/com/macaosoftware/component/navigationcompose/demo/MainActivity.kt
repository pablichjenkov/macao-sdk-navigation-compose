package com.macaosoftware.component.navigationcompose.demo

import android.content.res.Configuration
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.macaosoftware.app.MacaoApplication
import com.macaosoftware.app.MacaoApplicationState
import com.macaosoftware.app.startup.task.StartupTaskRunnerDefault
import com.macaosoftware.component.navigationcompose.demo.startup.initializers.DemoRootGraphInitializer
import com.macaosoftware.component.navigationcompose.demo.startup.tasks.DatabaseMigrationStartupTask
import com.macaosoftware.component.navigationcompose.demo.startup.tasks.FirebaseConfigStartupTask
import com.macaosoftware.component.navigationcompose.demo.startup.tasks.LaunchDarklyStartupTask


class MainActivity : ComponentActivity() {

    private val startupTasks = listOf(
        DatabaseMigrationStartupTask(),
        LaunchDarklyStartupTask(),
        FirebaseConfigStartupTask()
    )

    private val macaoApplicationState = MacaoApplicationState(
        koinModulesInitializer = AndroidKoinModulesInitializer(this),
        startupTaskRunner = StartupTaskRunnerDefault(startupTasks),
        rootGraphInitializer = DemoRootGraphInitializer()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        checkEnableStrictMode()
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MacaoApplication(
                    applicationState = macaoApplicationState
                )
            }
        }

    }

    private fun checkEnableStrictMode() {
        StrictMode.setThreadPolicy(
            ThreadPolicy.Builder()
                //.detectDiskReads()
                //.detectDiskWrites()
                //.detectNetwork() // or .detectAll() for all detectable problems
                .detectAll()
                .penaltyLog()
                .build()
        )
        StrictMode.setVmPolicy(
            VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                //.detectAll()
                .penaltyLog()
                //.penaltyDeath()
                .build()
        )
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show()
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show()
        }
    }

}
