package com.macaosoftware.component.drawer

import com.macaosoftware.component.viewmodel.ComponentViewModel

abstract class DrawerViewModel : ComponentViewModel() {

    abstract val drawerStatePresenter: DrawerStatePresenter
}
