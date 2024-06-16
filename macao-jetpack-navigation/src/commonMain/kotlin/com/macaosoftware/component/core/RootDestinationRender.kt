package com.macaosoftware.component.core

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelStoreOwner

interface RootDestinationRender {

    fun getRenderType(): String

    @Composable
    fun Content(
        destinationInfo: DestinationInfo,
        viewModelStoreOwner: ViewModelStoreOwner
    )
}
