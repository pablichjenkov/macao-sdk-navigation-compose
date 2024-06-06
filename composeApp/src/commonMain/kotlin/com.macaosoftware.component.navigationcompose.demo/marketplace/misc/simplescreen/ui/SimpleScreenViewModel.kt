package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen.ui

import androidx.compose.ui.graphics.Color
import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.viewmodel.DestinationViewModel

class SimpleScreenViewModel(
    val bgColor: Color,
    val resultHandler: (Result) -> Unit
) : DestinationViewModel() {

    override fun onAttach(destinationInfo: DestinationInfo) {

    }

    override fun onStart() {

    }

    override fun onStop() {

    }

    override fun onDetach() {

    }

    fun nextClick() {

    }

    override fun handleBackPressed() {
        resultHandler.invoke(Result.UserPressBack)
    }

    sealed class Result {
        object UserPressBack : Result()
        object ScreenIsDone : Result()
    }
}
