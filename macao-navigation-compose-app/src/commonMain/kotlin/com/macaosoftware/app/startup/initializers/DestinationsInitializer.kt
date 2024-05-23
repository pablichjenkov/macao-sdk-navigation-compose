package com.macaosoftware.app.startup.initializers

import com.macaosoftware.component.core.RootDestinationPresenter
import com.macaosoftware.util.MacaoResult
import org.koin.core.component.KoinComponent
import kotlin.native.ObjCName

@ObjCName("RootComponentProvider")
interface DestinationsInitializer {
    fun shouldShowLoader(): Boolean
    suspend fun initialize(
        koinComponent: KoinComponent
    ): MacaoResult<RootDestinationPresenter>
}
