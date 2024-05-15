package com.macaosoftware.component.core

object NavigationComponentDefaults {

    fun createLifecycleHandler(): NavigationComponentDefaultLifecycleHandler {
        return NavigationComponentDefaultLifecycleHandler()
    }

}

class NavigationComponentDefaultLifecycleHandler : NavigationComponent.LifecycleHandler {

    override fun NavigationComponent.navigationComponentLifecycleStart() {

        if (getComponent().startedFromDeepLink) {
            return
        }

        val activeComponentCopy = activeComponent.value
        if (activeComponentCopy != null && backStack.size() > 0) {
            println("${getComponent().instanceId()}::onStart() with activeChild = ${activeComponentCopy.instanceId()}")
            activeComponentCopy.dispatchActive()
            return
        }

        if (childComponents.isNotEmpty()) {
            println("${getComponent().instanceId()}::onStart(). Pushing selectedIndex = $selectedIndex, children.size = ${childComponents.size}")
            navigator.push(childComponents[selectedIndex])
        } else {
            println("${getComponent().instanceId()}::onStart() with childComponents empty")
        }
    }

    override fun NavigationComponent.navigationComponentLifecycleStop() {
        println("${getComponent().instanceId()}::onStop()")
        activeComponent.value?.dispatchInactive()
    }

    override fun NavigationComponent.navigationComponentLifecycleDestroy() {
        println("${getComponent().instanceId()}::onDetach()")
    }
}
