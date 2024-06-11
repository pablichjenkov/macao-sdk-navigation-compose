package com.macaosoftware.component.core

import androidx.compose.runtime.Composable

interface RootDestinationRender {

    fun getRenderType(): String

    @Composable
    fun Content(destinationInfo: DestinationInfo)
}
