package com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.ui

import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.core.DestinationRendersRegistry
import com.macaosoftware.component.drawer.DrawerStatePresenter
import com.macaosoftware.component.drawer.DrawerViewModel
import com.macaosoftware.component.drawer.toDrawerNavItem
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.domain.DemoDrawerStateLoaderUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class DemoDrawerViewModel(
    private val stateLoaderUseCase: DemoDrawerStateLoaderUseCase,
    override val drawerStatePresenter: DrawerStatePresenter,
    override val destinationRendersRegistry: DestinationRendersRegistry
) : DrawerViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onStart(destinationInfo: DestinationInfo) {
        println("DemoDrawerViewModel[${instanceId()}]::onStart()")

        coroutineScope.launch {
            val childDestinations = stateLoaderUseCase
                .loadChildrenDestinations(destinationInfo.dataSource)
            val navItemDecoNewList = childDestinations.map {
                it.toDrawerNavItem()
            }
            drawerStatePresenter.setNavItemsDeco(navItemDecoNewList)
        }
    }

    override fun onStop() {
        println("DemoDrawerViewModel[${instanceId()}]::onStop()")
    }

    override fun handleBackPressed() {
        println("DemoDrawerViewModel[${instanceId()}]::handleBackPressed()")
    }

}
