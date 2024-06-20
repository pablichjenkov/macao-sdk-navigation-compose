package com.macaosoftware.component.navigationcompose.demo.marketplace.entrypoints

import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.core.DestinationRendersRegistry
import com.macaosoftware.component.stack.StackStatePresenter
import com.macaosoftware.component.stack.StackViewModel

class AppCoordinatorV2ViewModel(
    override val stackStatePresenter: StackStatePresenter,
    override val destinationRendersRegistry: DestinationRendersRegistry
) : StackViewModel() {

    override fun onStart(destinationInfo: DestinationInfo) {
        TODO("Not yet implemented")
    }

    override fun onStop() {
        TODO("Not yet implemented")
    }

    override fun handleBackPressed() {
        super.handleBackPressed()
    }
}