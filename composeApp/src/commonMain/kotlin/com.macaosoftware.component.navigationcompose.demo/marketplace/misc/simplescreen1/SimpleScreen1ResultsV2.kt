package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1

import com.macaosoftware.component.core.DestinationResultV2

sealed class SimpleScreen1ResultV2 : DestinationResultV2() {

    class Success(val value: Int) : SimpleScreen1ResultV2()
    class Error(val error: String) : SimpleScreen1ResultV2()
}
