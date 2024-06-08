package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.result

import com.macaosoftware.component.core.DestinationResult

sealed class NotFoundResult : DestinationResult {

    class Error(val error: String) : NotFoundResult()
}
