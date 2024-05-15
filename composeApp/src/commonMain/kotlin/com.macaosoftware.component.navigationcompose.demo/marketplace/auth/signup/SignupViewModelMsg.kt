package com.macaosoftware.component.navigationcompose.demo.marketplace.auth.signup

sealed class SignupViewModelMsg {
    object OnGoBack : SignupViewModelMsg()
    class OnSuccess() : SignupViewModelMsg()
}
