package com.macaosoftware.component.viewmodel

import androidx.lifecycle.ViewModel

abstract class DestinationViewModel : ViewModel(), DestinationViewModelLifecycle {

    open fun handleBackPressed() {
    }

    open fun instanceId(): String {
        val addressLast5 = this.toString().let { it.substring(it.length - 5) }
        return "${this::class.simpleName}_${addressLast5}"
    }
}
