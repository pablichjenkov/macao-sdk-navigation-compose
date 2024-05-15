package com.macaosoftware.component.drawer

import com.macaosoftware.component.core.Component
import com.macaosoftware.component.core.NavigationComponent
import com.macaosoftware.component.core.NavigationComponentDefaults
import com.macaosoftware.component.stack.AddAllPushStrategy
import com.macaosoftware.component.stack.PushStrategy
import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.plugin.CoroutineDispatchers

abstract class DrawerComponentViewModel(
    protected val drawerComponent: DrawerComponent<DrawerComponentViewModel>,
    private val lifecycleHandler: NavigationComponent.LifecycleHandler =
        NavigationComponentDefaults.createLifecycleHandler(),
    val dispatchers: CoroutineDispatchers = CoroutineDispatchers.Default,
    val pushStrategy: PushStrategy<Component> = AddAllPushStrategy(),
) : ComponentViewModel(),
    NavigationComponent.LifecycleHandler by lifecycleHandler {

    abstract val drawerStatePresenter: DrawerStatePresenter
}
