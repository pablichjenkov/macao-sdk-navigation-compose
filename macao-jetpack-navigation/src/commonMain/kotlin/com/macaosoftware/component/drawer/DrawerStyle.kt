package com.macaosoftware.component.drawer

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.macaosoftware.component.core.DestinationRender
import com.macaosoftware.component.core.DestinationInfo

data class DrawerStyle(
    val alignment: Alignment.Horizontal = Alignment.Start,
    val bgColor: Color = Color.Cyan,
    val titleTextSize: TextUnit = 20.sp,
    val descriptionTextSize: TextUnit = 16.sp,
    val unselectedColor: Color = Color.LightGray,
    val selectedColor: Color = Color.Gray,
    val itemTextColor: Color = Color.Black,
    val itemTextSize: TextUnit = 14.sp
)
