package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1

import androidx.compose.ui.graphics.Color
import com.macaosoftware.component.viewmodel.ComponentViewModel

class SimpleScreen1ViewModel(
    val bgColor: Color,
    val resultHandler: (Result) -> Unit
) : ComponentViewModel() {

    override fun onAttach() {

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
