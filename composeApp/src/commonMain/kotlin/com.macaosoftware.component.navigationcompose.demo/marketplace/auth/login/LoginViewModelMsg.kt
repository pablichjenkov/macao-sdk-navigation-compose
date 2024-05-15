package com.macaosoftware.component.navigationcompose.demo.marketplace.auth.login

sealed class LoginViewModelMsg {
    object OnLoginWithEmailLinkClick : LoginViewModelMsg()
    object OnCreateAccountClick : LoginViewModelMsg()
    object OnForgotCredentialsClick : LoginViewModelMsg()
    class OnSuccess() : LoginViewModelMsg()
    class OnError() : LoginViewModelMsg()
}