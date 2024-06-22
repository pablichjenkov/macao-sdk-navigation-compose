package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.macaosoftware.component.core.DestinationResult
import com.macaosoftware.component.core.ResultHandler
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.result.SimpleScreen1Result
import com.macaosoftware.component.util.BackPressHandler

@Composable
fun SimpleScreen1View(
    viewModel: SimpleScreen1ViewModel,
    modifier: Modifier = Modifier.fillMaxSize(),
    resultHandler: ResultHandler<DestinationResult<SimpleScreen1Result>>
) {
    BackPressHandler {
        viewModel.handleBackPressed()
    }

    LaunchedEffect(viewModel) {
        viewModel.resultFlow.collect { resultHandler.process(it) }
    }

    Column(
        modifier = modifier.fillMaxSize()
            .background(color = viewModel.bgColor)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is another screen render type")
        Button(
            modifier = Modifier.padding(vertical = 40.dp),
            onClick = {
                viewModel.goBackClick()
            }
        ) {
            Text(text = "Send Result")
        }
    }
}