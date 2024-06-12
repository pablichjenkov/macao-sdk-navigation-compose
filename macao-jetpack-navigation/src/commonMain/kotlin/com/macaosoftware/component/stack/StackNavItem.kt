package com.macaosoftware.component.stack

import com.macaosoftware.component.core.DestinationInfo

data class StackNavItem(
    val destinationInfo: DestinationInfo,
)

fun DestinationInfo.toStackNavItem(selected: Boolean = false): StackNavItem {
    return StackNavItem(
        destinationInfo = this
    )
}