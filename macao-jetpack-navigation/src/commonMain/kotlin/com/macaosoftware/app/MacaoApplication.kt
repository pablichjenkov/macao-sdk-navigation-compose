package com.macaosoftware.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.macaosoftware.component.core.DestinationRendersRegistry
import org.koin.compose.KoinIsolatedContext
import org.koin.compose.getKoin

@Composable
fun MacaoApplication(
    applicationState: MacaoApplicationState
) = when (val stage = applicationState.startupStage.value) {

    Created -> {
        SideEffect {
            applicationState.initialize()
        }
    }

    is Initializing -> {
        InitializationHandler(stage)
    }

    is InitializationError -> {
        SplashScreen(stage.error.message)
    }

    is InitializationSuccess -> {

        KoinIsolatedContext(context = stage.isolatedKoinComponent.koinApplication) {

            getKoin().get<DestinationRendersRegistry>()
                .renderForRoot(stage.rootDestinationInfo.renderType)
                .Content(stage.rootDestinationInfo)
        }
    }
}

@Composable
private fun InitializationHandler(
    initializing: Initializing
) = when (initializing) {

//    Initializing.KoinRootModule -> {
//        // No-op
//    }

    is Initializing.StartupTaskRunning -> {
        SplashScreen(initializing.task.name())
    }

    Initializing.FetchingRemoteNavigationRootGraph -> {
        SplashScreen("Fetching Root Graph remotely")
    }
}

@Composable
private fun SplashScreen(text: String) {
    Box(Modifier.fillMaxSize().background(Color.LightGray)) {
        BasicText(
            modifier = Modifier.wrapContentSize().align(Alignment.Center),
            text = text
        )
    }
}
