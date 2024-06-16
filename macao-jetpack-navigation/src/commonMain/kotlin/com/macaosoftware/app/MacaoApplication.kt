package com.macaosoftware.app

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
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
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.macaosoftware.component.core.DestinationRendersRegistry
import org.koin.compose.KoinIsolatedContext
import org.koin.compose.getKoin
import org.koin.core.component.getScopeName

private const val ErrorNullViewModelStoreOwner = """
   Macao Application needs a ViewModelStoreOwner to properly work.
"""

@Composable
fun MacaoApplication(
    applicationState: MacaoApplicationState
) {
    // TODO Animated content doesn't seem to resolve the white flashing screen problem
    val stage = applicationState.startupStage.value
    AnimatedContent(
        targetState = stage,
        contentKey = { targetState ->
            targetState.getScopeName().type.simpleName
        },
        transitionSpec = {
            fadeIn(animationSpec = tween()) togetherWith
                    fadeOut(animationSpec = tween())
        }
    ) { targetStage ->
        // It's important to use targetStage and not state, as its critical to ensure
        // a successful lookup of all the incoming and outgoing content during
        // content transform.
        when (targetStage) {

            Created -> {
                SideEffect {
                    applicationState.initialize()
                }
            }

            is Initializing -> {
                InitializationHandler(targetStage)
            }

            is InitializationError -> {
                SplashScreen(targetStage.error.message, Color.Red)
            }

            is InitializationSuccess -> {

                val viewModelStoreOwner = LocalViewModelStoreOwner.current
                    ?: run {
                        SplashScreen(ErrorNullViewModelStoreOwner, Color.Red)
                        return@AnimatedContent
                    }

                KoinIsolatedContext(context = targetStage.isolatedKoinComponent.koinApplication) {
                    getKoin().get<DestinationRendersRegistry>()
                        .renderForRoot(targetStage.rootDestinationInfo.renderType)
                        .Content(
                            targetStage.rootDestinationInfo,
                            viewModelStoreOwner
                        )
                }
            }
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
