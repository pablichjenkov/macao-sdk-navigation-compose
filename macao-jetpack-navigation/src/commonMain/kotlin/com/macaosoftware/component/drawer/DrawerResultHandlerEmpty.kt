package com.macaosoftware.component.drawer

import com.macaosoftware.component.core.DestinationResult

class DrawerResultHandlerEmpty : DrawerResultHandler<Any>() {

    override fun getRenderType(): String {
        return "TypeNotFound"
    }

    override fun process(result: DestinationResult<Any>) {
        // no-op
    }
}
