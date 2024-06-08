package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen.result

import com.macaosoftware.component.core.DestinationResult

sealed class SimpleScreenResult() : DestinationResult {

    class Success(val value: Int) : SimpleScreenResult()
    class Error(val error: String) : SimpleScreenResult()
}
