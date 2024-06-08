package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen

import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.SimpleScreen1ResultV2
import com.macaosoftware.util.MacaoResult

val SimpleScreen1ResultHandler  = { macaoResult: MacaoResult<SimpleScreen1ResultV2> ->

    when (macaoResult) {
        is MacaoResult.Error -> {
            macaoResult.error
        }
        is MacaoResult.Success -> {
            macaoResult.value
        }
    }
}
