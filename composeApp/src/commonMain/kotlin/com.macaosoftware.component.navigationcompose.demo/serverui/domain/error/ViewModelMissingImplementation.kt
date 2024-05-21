package com.macaosoftware.component.navigationcompose.demo.serverui.domain.error

import com.macaosoftware.component.viewmodel.ComponentViewModel

class ViewModelMissingImplementation(
    private val componentType: String
) : ComponentViewModel() {

    override fun instanceId(): String {
        return "$componentType, ID = ${super.instanceId()}"
    }

    override fun onAttach() {
        println("ViewModelMissingImplementation::onAttach")
    }

    override fun onStart() {
        println("ViewModelMissingImplementation::onStart")
    }

    override fun onStop() {
        println("ViewModelMissingImplementation::onStop")
    }

    override fun onDetach() {
        println("ViewModelMissingImplementation::onDetach")
    }


}
