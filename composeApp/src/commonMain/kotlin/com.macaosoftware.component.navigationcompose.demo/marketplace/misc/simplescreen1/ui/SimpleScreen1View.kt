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
import com.macaosoftware.component.core.Cancel
import com.macaosoftware.component.core.DestinationResult
import com.macaosoftware.component.core.ResultAdapter
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.SimpleScreen1Result
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.SimpleScreen1ResultV2
import com.macaosoftware.component.util.BackPressHandler
import com.macaosoftware.util.MacaoResult

@Composable
fun SimpleScreen1View(
    viewModel: SimpleScreen1ViewModel,
    modifier: Modifier = Modifier.fillMaxSize(),
    resultAdapter: ResultAdapter<DestinationResult<SimpleScreen1Result>>
) {
    BackPressHandler {
        viewModel.handleBackPressed()
        resultAdapter.process(DestinationResult.Error(Cancel))
    }

    LaunchedEffect(viewModel) {
        viewModel.resultFlow.collect {
            resultAdapter.process(DestinationResult.Success(SimpleScreen1Result()))
        }
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
            Text(text = "Go Back with Result")
        }
    }
}