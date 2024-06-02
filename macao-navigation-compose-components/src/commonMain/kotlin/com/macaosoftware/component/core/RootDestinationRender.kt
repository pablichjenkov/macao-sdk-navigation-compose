package com.macaosoftware.component.core

import androidx.compose.runtime.Composable

interface RootDestinationRender {

    fun getRoute(): String
    fun getRenderType(): String

    @Composable
    fun Content()
}
