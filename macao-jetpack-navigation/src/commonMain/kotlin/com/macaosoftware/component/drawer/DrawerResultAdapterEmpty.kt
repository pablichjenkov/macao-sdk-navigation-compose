package com.macaosoftware.component.drawer

import com.macaosoftware.component.core.DestinationResult

class DrawerResultAdapterEmpty : DrawerResultProcessor() {

    override fun getRenderType(): String {
        return "TypeNotFound"
    }

    override fun process(result: DestinationResult) {
        // no-op
    }
}
