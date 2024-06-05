package com.macaosoftware.component.core

import androidx.compose.ui.graphics.vector.ImageVector

data class DestinationInfo(
    // Data
    val route: String,
    val renderType: String,
    val dataSource: String,

    // Presentation
    val label: String,
    val icon: ImageVector,
    val badgeText: String? = null
)
