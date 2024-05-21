package com.macaosoftware.component.drawer

interface DrawerViewModelFactory<VM : DrawerViewModel> {
    fun create(): VM
}
