package com.macaosoftware.component.navigationcompose.demo.marketplace.auth.forget

sealed class ForgotCredentialsViewModelMsg {
    object OnGoBack : ForgotCredentialsViewModelMsg()
    object OnCreateAccountClick : ForgotCredentialsViewModelMsg()
    class OnSuccess() : ForgotCredentialsViewModelMsg()
}