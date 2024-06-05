package com.macaosoftware.component.viewmodel

import com.macaosoftware.component.core.DestinationInfo

interface ComponentViewModelLifecycle {
    fun onAttach(destinationInfo: DestinationInfo)
    fun onStart()
    fun onStop()
    fun onDetach()
}
