package com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer

import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.core.DestinationRendersRegistry
import com.macaosoftware.component.drawer.DrawerStatePresenterDefault
import com.macaosoftware.component.drawer.DrawerViewModel
import com.macaosoftware.component.drawer.toDrawerNavItem
import com.macaosoftware.component.navigationcompose.demo.serverui.domain.usecase.LoadChildrenDestinationUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class DemoDrawerViewModel(
    private val childrenDestinationLoaderUseCase: LoadChildrenDestinationUseCase,
    override val drawerStatePresenter: DrawerStatePresenterDefault,
    override val destinationRendersRegistry: DestinationRendersRegistry
) : DrawerViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onAttach(destinationInfo: DestinationInfo) {
        println("DrawerViewModelDefault[${instanceId()}]::onAttach()")
        coroutineScope.launch {
            val childDestinations = childrenDestinationLoaderUseCase
                .loadDestinations(destinationInfo.dataSource)
            val navItemDecoNewList = childDestinations.map {
                it.toDrawerNavItem()
            }
            drawerStatePresenter.setNavItemsDeco(navItemDecoNewList)
        }
    }

    override fun onStart() {
        println("DrawerViewModelDefault[${instanceId()}]::onStart()")
    }

    override fun onStop() {
        println("DrawerViewModelDefault[${instanceId()}]::onStop()")
    }

    override fun onDetach() {
        println("DrawerViewModelDefault[${instanceId()}]::onDetach()")
    }

    override fun handleBackPressed() {
    }

}
