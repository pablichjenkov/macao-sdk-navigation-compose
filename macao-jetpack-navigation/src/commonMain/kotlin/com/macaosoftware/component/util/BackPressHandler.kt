package com.macaosoftware.component.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LifecycleStartEffect
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

    LifecycleStartEffect(key1 = LocalLifecycleOwner.current) {

        if (enabled) {
            backPressDispatcher.subscribe(backPressMacaoPluginCallback)
        }

        onStopOrDispose { backPressDispatcher.unsubscribe(backPressMacaoPluginCallback) }
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
