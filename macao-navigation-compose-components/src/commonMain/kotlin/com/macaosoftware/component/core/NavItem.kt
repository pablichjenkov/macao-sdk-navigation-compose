package com.macaosoftware.component.core

import androidx.compose.ui.graphics.vector.ImageVector
import com.macaosoftware.component.ComposableStateMapper

data class NavItem(
    val composableStateMapper: ComposableStateMapper,
    val label: String,
    val icon: ImageVector,
    val badgeText: String? = null
)
