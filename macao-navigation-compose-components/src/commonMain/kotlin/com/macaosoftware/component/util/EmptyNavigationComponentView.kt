package com.macaosoftware.component.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

private const val EmptyStackMessage =
    "NavigationComponent Empty Stack!.\nYou either did not call setNavItem(...) or children are zero"

@Composable
fun EmptyNavigationView() {
    var show by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(1.seconds)
        show = true
    }
    if (show) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center).padding(16.dp),
                text = EmptyStackMessage
            )
        }
    }
}
