package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen.ui

import androidx.compose.ui.graphics.Color
import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen.SimpleScreenResultV2
import com.macaosoftware.component.viewmodel.DestinationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class SimpleScreenViewModel(
    val bgColor: Color
) : DestinationViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val resultFlow = MutableSharedFlow<SimpleScreenResultV2>()

    override fun onAttach(destinationInfo: DestinationInfo) {

    }

    override fun onStart() {

    }

    override fun onStop() {

    }

    override fun onDetach() {

    }

    fun sendResult() {
        coroutineScope.launch {
            resultFlow.emit(SimpleScreenResultV2.Success(100))
        }
    }

    override fun handleBackPressed() {

    }

}
