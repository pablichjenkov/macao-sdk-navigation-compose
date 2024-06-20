package com.macaosoftware.component.viewmodel

import com.macaosoftware.component.core.DestinationInfo

interface DestinationViewModelLifecycle {
    fun onStart(destinationInfo: DestinationInfo)
    fun onStop()
}
