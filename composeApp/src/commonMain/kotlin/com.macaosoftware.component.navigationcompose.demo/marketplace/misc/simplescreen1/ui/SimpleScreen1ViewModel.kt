package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.ui

import androidx.compose.ui.graphics.Color
import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.SimpleScreen1ResultV2
import com.macaosoftware.component.viewmodel.DestinationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class SimpleScreen1ViewModel(
    val bgColor: Color
) : DestinationViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val resultFlow = MutableSharedFlow<SimpleScreen1ResultV2>()

    override fun onAttach(destinationInfo: DestinationInfo) {

    }

    override fun onStart() {

    }

    override fun onStop() {

    }

    override fun onDetach() {

    }

    fun goBackClick() {
        coroutineScope.launch {
            resultFlow.emit(SimpleScreen1ResultV2.Error("Error in Simple Screen 1"))
        }
    }

    override fun handleBackPressed() {
    }

}
