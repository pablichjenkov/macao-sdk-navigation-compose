package com.macaosoftware.component.viewmodel

abstract class ComponentViewModelLifecycle {
    protected abstract fun onAttach()
    protected abstract fun onStart()
    protected abstract fun onStop()
    protected abstract fun onDetach()
}
