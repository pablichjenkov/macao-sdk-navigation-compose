package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.ui

import androidx.compose.ui.graphics.Color
import com.macaosoftware.component.core.Cancel
import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.core.DestinationResult
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.result.SimpleScreen1Result
import com.macaosoftware.component.viewmodel.DestinationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class SimpleScreen1ViewModel(
    val bgColor: Color
) : DestinationViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val resultFlow = MutableSharedFlow<DestinationResult<SimpleScreen1Result>>()

    override fun onStart(destinationInfo: DestinationInfo) {

    }

    override fun onStop() {

    }

    fun goBackClick() {
        coroutineScope.launch {
            resultFlow.emit(
                DestinationResult.Success(SimpleScreen1Result())
            )
        }
    }

    override fun handleBackPressed() {
        coroutineScope.launch {
            resultFlow.emit(
                DestinationResult.Error(Cancel)
            )
        }
    }

}
