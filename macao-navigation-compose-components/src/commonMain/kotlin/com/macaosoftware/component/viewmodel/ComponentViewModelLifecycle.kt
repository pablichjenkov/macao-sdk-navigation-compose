package com.macaosoftware.component.viewmodel

interface ComponentViewModelLifecycle {
    fun onAttach()
    fun onStart()
    fun onStop()
    fun onDetach()
}
