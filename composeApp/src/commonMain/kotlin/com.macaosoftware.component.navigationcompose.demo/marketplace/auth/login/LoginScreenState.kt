package com.macaosoftware.component.navigationcompose.demo.marketplace.auth.login

import com.macaosoftware.component.navigationcompose.demo.marketplace.auth.signup.model.SignupForm

sealed class LoginScreenState {
    data class EnteringData(
        val signupForm: SignupForm
    ) : LoginScreenState()

    data object OngoingRequest : LoginScreenState()

    data class PresentResult(
        val success: Boolean
    ) : LoginScreenState()
}