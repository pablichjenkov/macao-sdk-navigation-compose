package com.macaosoftware.component.core

import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val destinationPresenter: DestinationPresenter,
    val label: String,
    val icon: ImageVector,
    val badgeText: String? = null
)
