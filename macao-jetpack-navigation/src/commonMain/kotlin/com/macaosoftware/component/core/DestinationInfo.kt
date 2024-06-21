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

    // To pass input params from parents down to children.
    // Basically is the "Props" pattern taken from ReactJS.
    var props: Bundle? = null
)
