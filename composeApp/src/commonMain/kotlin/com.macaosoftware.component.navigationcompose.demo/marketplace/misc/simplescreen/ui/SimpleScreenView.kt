package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen.ui

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
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen.result.SimpleScreenResult
import com.macaosoftware.component.util.BackPressHandler

@Composable
fun SimpleScreenView(
    viewModel: SimpleScreenViewModel,
    modifier: Modifier = Modifier.fillMaxSize(),
    resultHandler: ResultHandler<DestinationResult<SimpleScreenResult>>
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
        Button(
            modifier = Modifier.padding(vertical = 40.dp),
            onClick = {
                viewModel.sendResult()
            }
        ) {
            Text(text = "Send Result")
        }
    }
}
