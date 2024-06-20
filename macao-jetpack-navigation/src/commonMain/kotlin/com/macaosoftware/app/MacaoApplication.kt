package com.macaosoftware.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import org.koin.compose.KoinIsolatedContext

private const val ErrorNullViewModelStoreOwner = """
   Macao Application needs a ViewModelStoreOwner to properly work.
"""

@Composable
fun MacaoApplication(
    applicationState: MacaoApplicationState
) {

    LifecycleStartEffect(key1 = applicationState) {
        applicationState.start()
        onStopOrDispose {
            // no-op
        }
    }

    when (val stage = applicationState.startupStage.value) {

        JustCreated,
        Initializing.KoinRootModule -> {
            // no-op, Koin root module initialization shouldn't take more than a few nanoseconds
        }

        is Initializing -> {
            InitializationHandler(stage)
        }

        is InitializationError -> {
            SplashScreen(stage.error.message, Color.Red)
        }

        is InitializationSuccess -> {

            val viewModelStoreOwner = LocalViewModelStoreOwner.current
                ?: run {
                    SplashScreen(ErrorNullViewModelStoreOwner, Color.Red)
                    return
                }

            KoinIsolatedContext(context = stage.isolatedKoinComponent.koinApplication) {

                stage.rootDestinationRender.Content(
                    stage.rootDestinationInfo,
                    viewModelStoreOwner
                )
                // TODO: Investigate why using just bellow code the this Composable doesn't flash when it
                //  renders. However, when using DemoDrawer Composable it flashes.
                // Box(Modifier.fillMaxSize().background(Color.Magenta))
            }
        }
    }
}

@Composable
private fun InitializationHandler(
    initializing: Initializing
) = when (initializing) {

    Initializing.KoinRootModule -> {
        // no-op, Koin root module initialization shouldn't take more than a few nanoseconds
    }

    is Initializing.StartupTaskRunning -> {
        SplashScreen(initializing.task.name(), Color.Yellow)
    }

    Initializing.FetchingRemoteNavigationRootGraph -> {
        SplashScreen("Fetching Root Graph remotely", Color.Green)
    }
}

@Composable
private fun SplashScreen(text: String, color: Color) {
    Box(Modifier.fillMaxSize().background(color)) {
        BasicText(
            modifier = Modifier.wrapContentSize().align(Alignment.Center),
            text = text
        )
    }
}
