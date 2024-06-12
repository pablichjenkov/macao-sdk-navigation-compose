package com.macaosoftware.component.navigationcompose.demo.marketplace.privacy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AcceptPrivacyView() {

    //val widthClass = calculateWindowSizeClass().widthSizeClass
    val centered = false//widthClass == WindowWidthSizeClass.Expanded

    Scaffold(contentColor = MaterialTheme.colorScheme.onSurface, containerColor = MaterialTheme.colorScheme.surface) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize().padding(horizontal = 24.dp), contentAlignment = Alignment.TopCenter) {
            Column(Modifier.widthIn(max = 600.dp).verticalScroll(rememberScrollState(0)).fillMaxSize(), horizontalAlignment = if (centered) Alignment.CenterHorizontally else Alignment.Start) {
                Spacer(Modifier.height(96.dp))
                Surface(
                    shape = CircleShape, color = MaterialTheme.colorScheme.primary, modifier = Modifier.size(80.dp).then(
                        if (centered) {
                            Modifier.align(Alignment.CenterHorizontally)
                        } else {
                            Modifier
                        }
                    )
                ) {
                    Icon(imageVector = Icons.Rounded.Lock, contentDescription = "Icon", modifier = Modifier.padding(12.dp), tint = MaterialTheme.colorScheme.onPrimary)
                }
                Spacer(Modifier.height(24.dp))
                Text(
                    text = "We respect your privacy",
                    style = MaterialTheme.typography.headlineMedium,
                )
                Spacer(Modifier.height(12.dp))

                Column {
                    Text(
                        "We take your personal information seriously and are committed to keeping it secure." + " We only collect the necessary data to provide you with the best possible service" + " and never share or sell your information to third parties without your explicit consent.",
                        textAlign = TextAlign.Center.takeIf { centered })
                    Spacer(Modifier.height(24.dp))
                    //TermsAndConditions()
                }

                //if (widthClass != WindowWidthSizeClass.Compact) {
                    Spacer(Modifier.height(48.dp))
                    ButtonBar()
                //}
            }
            /*if (widthClass == WindowWidthSizeClass.Compact) {
                ButtonBar(Modifier.align(Alignment.BottomCenter))
            }*/
        }
    }
}

@Composable
private fun ButtonBar(modifier: Modifier = Modifier) {
    Column(modifier.padding(bottom = 12.dp)) {
        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
            Text("Accept")
        }
        TextButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.onSurface)) {
            Text("Reject")
        }
    }
}