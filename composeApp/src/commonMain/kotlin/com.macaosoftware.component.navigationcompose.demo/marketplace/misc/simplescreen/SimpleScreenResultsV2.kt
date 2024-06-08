package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen

import com.macaosoftware.component.core.DestinationResultV2

sealed class SimpleScreenResultV2 : DestinationResultV2() {

    class Success(val value: Int) : SimpleScreenResultV2()
    class Error(val error: String) : SimpleScreenResultV2()
}
