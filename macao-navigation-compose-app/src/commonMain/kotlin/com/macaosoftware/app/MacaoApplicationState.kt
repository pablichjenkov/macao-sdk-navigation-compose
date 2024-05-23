package com.macaosoftware.app

import androidx.compose.runtime.mutableStateOf
import com.macaosoftware.app.startup.initializers.DestinationsInitializer
import com.macaosoftware.app.startup.initializers.RootKoinModuleInitializer
import com.macaosoftware.app.startup.task.StartupTaskRunner
import com.macaosoftware.app.startup.task.StartupTaskStatus
import com.macaosoftware.component.core.RootDestinationPresenter
import com.macaosoftware.plugin.CoroutineDispatchers
import com.macaosoftware.util.MacaoResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.dsl.koinApplication

class MacaoApplicationState(
    private val rootKoinModuleInitializer: RootKoinModuleInitializer,
    private val startupTaskRunner: StartupTaskRunner,
    private val destinationsInitializer: DestinationsInitializer,
    private val dispatchers: CoroutineDispatchers = CoroutineDispatchers.Default
) {

    internal var stage = mutableStateOf<Stage>(Created)
    private val coroutineScope = CoroutineScope(dispatchers.main)

    fun initialize() = coroutineScope.launch {

        val koinApplication = withContext(dispatchers.default) {

            val rootModules = rootKoinModuleInitializer.initialize()

            koinApplication {
                printLogger()
                modules(rootModules)
            }
        }

        runStartupTasks(IsolatedKoinComponent(koinApplication))
    }

    private suspend fun runStartupTasks(isolatedKoinComponent: IsolatedKoinComponent) {

        startupTaskRunner
            .initialize(isolatedKoinComponent)
            .flowOn(dispatchers.default)
            .collect { status ->
                when (status) {
                    is StartupTaskStatus.Running -> {
                        stage.value = Initializing.StartupTask(status.taskName)
                    }

                    is StartupTaskStatus.CompleteError -> {
                        stage.value = InitializationError(status.errorMsg)
                    }

                    is StartupTaskStatus.CompleteSuccess -> {
                        initializeRootMetadata(isolatedKoinComponent)
                    }
                }
            }
    }

    private suspend fun initializeRootMetadata(isolatedKoinComponent: IsolatedKoinComponent) {

        if (destinationsInitializer.shouldShowLoader()) {
            stage.value = Initializing.RootMetadata
        }
        val result = withContext(dispatchers.default) {
            destinationsInitializer.initialize(isolatedKoinComponent)
        }

        when(result) {
            is MacaoResult.Error -> {
                stage.value = InitializationError(result.error.toString())
            }
            is MacaoResult.Success -> {
                stage.value = InitializationSuccess(
                    isolatedKoinComponent = isolatedKoinComponent,
                    rootDestinationPresenter = result.value
                )
            }
        }
    }

}

internal sealed class Stage
internal data object Created : Stage()

internal sealed class Initializing : Stage() {
    // data object KoinRootModule : Initializing()
    data class StartupTask(val taskName: String) : Initializing()
    data object RootMetadata : Initializing()
}

internal class InitializationError(val errorMsg: String) : Stage()
internal class InitializationSuccess(
    val isolatedKoinComponent: IsolatedKoinComponent,
    val rootDestinationPresenter: RootDestinationPresenter
) : Stage()
