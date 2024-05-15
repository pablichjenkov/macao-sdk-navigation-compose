package com.macaosoftware.component.navigationcompose.demo.marketplace.auth

import com.macaosoftware.component.navigationcompose.demo.marketplace.auth.AuthViewModel
import com.macaosoftware.component.stack.StackComponent
import com.macaosoftware.component.stack.StackComponentViewModelFactory
import com.macaosoftware.component.stack.StackStatePresenter
import com.macaosoftware.plugin.account.AccountPlugin

class AuthViewModelFactory(
    private val accountPlugin: AccountPlugin,
    private val stackStatePresenter: StackStatePresenter
) : StackComponentViewModelFactory<AuthViewModel> {

    override fun create(stackComponent: StackComponent<AuthViewModel>): AuthViewModel {
        return AuthViewModel(stackComponent, stackStatePresenter, accountPlugin)
    }
}
