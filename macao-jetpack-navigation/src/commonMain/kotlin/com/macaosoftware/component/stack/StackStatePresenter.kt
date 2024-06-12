package com.macaosoftware.component.stack

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

interface StackStatePresenter {
    val showFloatingButton: State<Boolean>
    val navItemsState: State<List<StackNavItem>>
}

class StackStatePresenterDefault(
    navItemDecoList: List<StackNavItem>
) : StackStatePresenter {

    private var _showFloatingButton = mutableStateOf(false)
    override var showFloatingButton = _showFloatingButton

    private var _navItemsState = mutableStateOf(navItemDecoList)
    override val navItemsState: State<List<StackNavItem>> = _navItemsState
}
