package com.macaosoftware.component.drawer

import com.macaosoftware.component.core.DestinationRendersRegistry
import com.macaosoftware.component.viewmodel.DestinationViewModel

abstract class DrawerViewModel : DestinationViewModel() {

    abstract val drawerStatePresenter: DrawerStatePresenter
    abstract val destinationRendersRegistry: DestinationRendersRegistry
}
