package com.macaosoftware.app

import androidx.compose.runtime.mutableStateOf
import com.macaosoftware.app.di.IsolatedKoinComponent
import com.macaosoftware.app.startup.initializers.KoinModulesInitializer
import com.macaosoftware.app.startup.initializers.RootGraphInitializer
import com.macaosoftware.app.startup.initializers.RootGraphInitializerError
import com.macaosoftware.app.startup.task.StartupTask
import com.macaosoftware.app.startup.task.StartupTaskRunner
import com.macaosoftware.app.startup.task.StartupTasksEvents
import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.core.DestinationRender
import com.macaosoftware.component.core.DestinationRendersRegistry
import com.macaosoftware.component.core.RootDestinationRender
import com.macaosoftware.component.drawer.DrawerResultAdapter
import com.macaosoftware.plugin.CoroutineDispatchers
import com.macaosoftware.plugin.getCoroutineDispatchers
import com.macaosoftware.util.MacaoResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.dsl.koinApplication

class MacaoApplicationState(
    private val koinModulesInitializer: KoinModulesInitializer,
    private val startupTaskRunner: StartupTaskRunner,
    private val rootGraphInitializer: RootGraphInitializer,
    private val dispatchers: CoroutineDispatchers = getCoroutineDispatchers()
) {

    internal var startupStage = mutableStateOf<StartupStage>(JustCreated)
    private val coroutineScope = CoroutineScope(dispatchers.main)
    private var isInitializedSuccess = false

    /**
     * Triggers a new initialization flow only if the Application hasn't been successfully initialized
     * before.
     * See: isInitializedSuccess
     * */
    fun start() = coroutineScope.launch {

        // If it was already initialized we just return
        if (isInitializedSuccess) return@launch

        initializeKoinRootModule()
    }

    /**
     * Triggers a new initialization flow without considering isInitializedSuccess
     * */
    fun refreshInitialization() = coroutineScope.launch {
        initializeKoinRootModule()
    }

    private suspend fun initializeKoinRootModule() = coroutineScope.launch {

        startupStage.value = Initializing.KoinRootModule

        val koinApplication = withContext(dispatchers.default) {

            val rootModules = koinModulesInitializer.initialize()

            koinApplication {
                allowOverride(true)
                printLogger()
                modules(rootModules)
            }
        }

        // Register all DestinationRender implementations in DestinationRendersRegistry
        with(koinApplication.koin) {

            val destinationRendersRegistry = get<DestinationRendersRegistry>()

            // Register all RootDestinationRender implementations provided
            // in all Koin Modules
            getAll<RootDestinationRender>().forEach {
                destinationRendersRegistry.addRoot(it)
            }

            // Register all DestinationRender implementations provided
            // in all Koin Modules
            getAll<DestinationRender>().forEach {
                destinationRendersRegistry.add(it)
            }

            // Register all DrawerResultAdapter implementations provided
            // in all Koin Modules
            getAll<DrawerResultAdapter<*>>().forEach {
                destinationRendersRegistry.addDrawerResultAdapter(it)
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
                    is StartupTasksEvents.TaskRunning -> {
                        startupStage.value = Initializing.StartupTaskRunning(status.task)
                    }

                    is StartupTasksEvents.TaskFinishedWithError -> {
                        startupStage.value = InitializationFailure(status.error)
                    }

                    is StartupTasksEvents.AllTaskCompletedSuccess -> {
                        initializeRootMetadata(isolatedKoinComponent)
                    }
                }
            }
    }

    private suspend fun initializeRootMetadata(isolatedKoinComponent: IsolatedKoinComponent) {

        if (rootGraphInitializer.shouldShowLoader()) {
            startupStage.value = Initializing.FetchingRemoteNavigationRootGraph
        }
        val result = withContext(dispatchers.default) {
            rootGraphInitializer.initialize(isolatedKoinComponent)
        }
        when (result) {
            is MacaoResult.Error -> {
                startupStage.value = InitializationFailure(result.error)
            }

            is MacaoResult.Success -> {

                val rootDestinationRender = isolatedKoinComponent
                    .getKoin()
                    .get<DestinationRendersRegistry>()
                    .renderForRoot(result.value.renderType)

                startupStage.value = InitializationSuccess(
                    isolatedKoinComponent = isolatedKoinComponent,
                    rootDestinationInfo = result.value,
                    rootDestinationRender = rootDestinationRender
                )

                isInitializedSuccess = true
            }
        }
    }

}
