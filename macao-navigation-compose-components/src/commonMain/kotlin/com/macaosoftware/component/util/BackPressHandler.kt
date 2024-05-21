package com.macaosoftware.component.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.macaosoftware.plugin.BackPressDispatcherPlugin
import com.macaosoftware.plugin.DefaultBackPressDispatcherPlugin
import com.macaosoftware.plugin.ForwardBackPressCallback

/**
 * This [Composable] can be used with a [LocalBackPressedDispatcher] to intercept a back press.
 *
 * @param onBackPressed (Event) What to do when back is intercepted
 *
 */
@Composable
fun BackPressHandler(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    enabled: Boolean = true,
    onBackPressed: () -> Unit
) {

    val backPressDispatcher = LocalBackPressedDispatcher.current

    // Safely update the current `onBack` lambda when a new one is provided
    val currentOnBackPressed by rememberUpdatedState(onBackPressed)

    // Remember in Composition a back callback that calls the `onBackPressed` lambda
    val backPressMacaoPluginCallback = remember(lifecycleOwner) {
        /*
        This ForwardBackPressCallback encapsulating class due to JS compilation is unable
        to process anonymous objects inside a remember block.
        Uncomment to test: https://github.com/JetBrains/compose-jb/issues/2615
        object : BackPressedCallback() {
            override fun onBackPressed() {
                currentOnBackPressed()
            }
        }*/
        ForwardBackPressCallback {
            currentOnBackPressed()
        }
    }

    // If the enclosing lifecycleOwner changes, dispose and reset the effect
    DisposableEffect(key1 = lifecycleOwner) {
        // Create an observer that triggers our remembered callbacks
        // when the LifecycleOwner that contains this composable changes its state.
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                // println("component.instanceId()::Lifecycle Flow = ON_START, BackPressHandler Subscribing")
                if (enabled) {
                    backPressDispatcher.subscribe(backPressMacaoPluginCallback)
                }
            } else if (event == Lifecycle.Event.ON_STOP) {
                // println("component.instanceId()::Lifecycle Flow = ON_STOP, BackPressHandler Unsubscribing")
                backPressDispatcher.unsubscribe(backPressMacaoPluginCallback)
            }
        }

        if (enabled) {
            backPressDispatcher.subscribe(backPressMacaoPluginCallback)
        }

        // When the effect leaves the Composition, remove the observer to not leak the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            println("BackPressHandler::Disposing LifecycleEventObserver")
        }
    }

}

/**
 * This [CompositionLocal] is used to provide an [BackPressDispatcherPlugin]:
 *
 * ```
 * val backPressedDispatcher = AndroidBackPressedDispatcher
 *
 * CompositionLocalProvider(
 *     LocalBackPressedDispatcher provides backPressedDispatcher
 * ) { }
 * ```
 *
 * and setting up the callbacks with [BackPressHandler].
 */
val LocalBackPressedDispatcher =
    staticCompositionLocalOf<BackPressDispatcherPlugin> { DefaultBackPressDispatcherPlugin() }
