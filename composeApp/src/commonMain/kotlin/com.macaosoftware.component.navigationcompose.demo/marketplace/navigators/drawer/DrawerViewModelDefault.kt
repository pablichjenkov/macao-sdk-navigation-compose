package com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer

import com.macaosoftware.component.drawer.DrawerStatePresenterDefault
import com.macaosoftware.component.drawer.DrawerViewModel
import com.macaosoftware.component.drawer.toDrawerNavItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class DrawerViewModelDefault(
    private val serverUiHandler: DrawerSduiHandler,
    override val drawerStatePresenter: DrawerStatePresenterDefault
) : DrawerViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onAttach() {
        println("DrawerViewModelDefault[${instanceId()}]::onAttach()")
        coroutineScope.launch {
            val navItems = serverUiHandler.loadNavItems()
            val navItemDecoNewList = navItems.map { it.toDrawerNavItem() }
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
