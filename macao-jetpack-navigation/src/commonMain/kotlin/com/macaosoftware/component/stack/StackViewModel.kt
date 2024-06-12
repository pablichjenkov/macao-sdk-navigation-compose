package com.macaosoftware.component.stack

import com.macaosoftware.component.core.DestinationRendersRegistry
import com.macaosoftware.component.viewmodel.DestinationViewModel

abstract class StackViewModel : DestinationViewModel() {

    abstract val stackStatePresenter: StackStatePresenter
    abstract val destinationRendersRegistry: DestinationRendersRegistry
}
