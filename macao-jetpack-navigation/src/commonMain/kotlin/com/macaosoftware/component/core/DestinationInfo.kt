package com.macaosoftware.component.core

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.core.bundle.Bundle

data class DestinationInfo(
    // Remote Data
    val route: String,
    val renderType: String,
    val dataSource: String,

    // Presentation
    val label: String,
    val icon: ImageVector,
    val badgeText: String? = null,

    // Local Parameters
    var props: Bundle? = null
)
