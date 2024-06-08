package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.result

import com.macaosoftware.component.core.DestinationResult

sealed class SimpleScreen1Result : DestinationResult {

    class Success(val value: Int) : SimpleScreen1Result()
    class Error(val error: String) : SimpleScreen1Result()
}

