package com.macaosoftware.app

import androidx.compose.runtime.mutableStateOf
import com.macaosoftware.component.ComposableStateMapper
import com.macaosoftware.plugin.CoroutineDispatchers
import com.macaosoftware.util.MacaoResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.dsl.koinApplication

class MacaoApplicationState(
    private val rootKoinModuleInitializer: RootKoinModuleInitializer,
    private val startupTaskRunner: StartupTaskRunner,
    private val rootComponentInitializer: RootComponentInitializer,
    private val dispatchers: CoroutineDispatchers = CoroutineDispatchers.Default
) {

    var stage = mutableStateOf<Stage>(Created)
    private val coroutineScope = CoroutineScope(dispatchers.main)

    fun initialize() = coroutineScope.launch {

        val koinApplication = withContext(dispatchers.default) {

            val rootModules = rootKoinModuleInitializer.initialize()

            koinApplication {
                printLogger()
                modules(rootModules)
            }
        }

        runStartupTasks(KoinInjector(koinApplication))
    }

    private suspend fun runStartupTasks(koinInjector: KoinInjector) {

        startupTaskRunner
            .initialize(koinInjector)
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
                        initializeRootMetadata(koinInjector)
                    }
                }
            }
    }

    private suspend fun initializeRootMetadata(koinInjector: KoinInjector) {

        if (rootComponentInitializer.shouldShowLoader()) {
            stage.value = Initializing.RootMetadata
        }
        val result = withContext(dispatchers.default) {
            rootComponentInitializer.initialize(koinInjector)
        }

        when(result) {
            is MacaoResult.Error -> {
                stage.value = InitializationError(result.error.toString())
            }
            is MacaoResult.Success -> {
                stage.value = InitializationSuccess(
                    koinRootContext = koinInjector,
                    stateMapper = result.value
                )
            }
        }
    }

}

sealed class Stage
data object Created : Stage()

sealed class Initializing : Stage() {
    // data object KoinRootModule : Initializing()
    data class StartupTask(val taskName: String) : Initializing()
    data object RootMetadata : Initializing()
}

class InitializationError(val errorMsg: String) : Stage()
class InitializationSuccess(
    val koinRootContext: KoinComponent,
    val stateMapper: ComposableStateMapper
) : Stage()
