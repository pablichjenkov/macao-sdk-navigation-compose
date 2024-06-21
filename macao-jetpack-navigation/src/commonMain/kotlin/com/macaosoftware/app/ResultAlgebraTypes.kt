package com.macaosoftware.app

import com.macaosoftware.app.di.IsolatedKoinComponent
import com.macaosoftware.app.startup.task.StartupTask
import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.core.RootDestinationRender

internal sealed class StartupStage

internal object JustCreated : StartupStage()

internal sealed class Initializing : StartupStage() {
    data object KoinRootModule : Initializing()
    data class StartupTaskRunning(val task: StartupTask) : Initializing()
    data object FetchingRemoteNavigationRootGraph : Initializing()
}

internal class InitializationFailure(val error: InitializationError) : StartupStage()

internal class InitializationSuccess(
    val isolatedKoinComponent: IsolatedKoinComponent,
    val rootDestinationInfo: DestinationInfo,
    val rootDestinationRender: RootDestinationRender
) : StartupStage()

interface InitializationError
