package com.macaosoftware.app.startup.initializers

import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.util.MacaoResult
import org.koin.core.component.KoinComponent
import kotlin.native.ObjCName

@ObjCName("RootGraphInitializer")
interface RootGraphInitializer {
    fun shouldShowLoader(): Boolean
    suspend fun initialize(
        koinComponent: KoinComponent
    ): MacaoResult<DestinationInfo, RootGraphInitializerError>
}
