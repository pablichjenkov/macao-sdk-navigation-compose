package com.macaosoftware.component.drawer

import com.macaosoftware.component.core.DestinationInfo

data class DrawerNavItem(
    val destinationInfo: DestinationInfo,
    var selected: Boolean
)

fun DestinationInfo.toDrawerNavItem(selected: Boolean = false): DrawerNavItem {
    return DrawerNavItem(
        destinationInfo = this,
        selected = selected
    )
}