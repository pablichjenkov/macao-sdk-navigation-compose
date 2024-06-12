package com.macaosoftware.component.stack

import com.macaosoftware.component.core.DestinationResult

class StackResultAdapterEmpty : StackResultAdapter<Any>() {

    override fun getRenderType(): String {
        return "TypeNotFound"
    }

    override fun process(result: DestinationResult<Any>) {
        // no-op
    }
}
