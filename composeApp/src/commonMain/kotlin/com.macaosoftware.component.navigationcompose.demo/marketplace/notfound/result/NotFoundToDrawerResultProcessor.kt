package com.macaosoftware.component.navigationcompose.demo.marketplace.notfound.result

import com.macaosoftware.component.core.DestinationResult
import com.macaosoftware.component.drawer.DrawerResultProcessor
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.result.NotFoundResult
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants

class NotFoundToDrawerResultProcessor
    : DrawerResultProcessor() {

    override fun getRenderType(): String {
        return ServerUiConstants.ComponentType.DestinationNotFound
    }

    override fun process(result: DestinationResult) {

        if (result !is NotFoundResult) return

        when (result) {
            is NotFoundResult.Error -> {
                println("NotFoundResult returned: ${result.error}")
                navController.popBackStack()
            }

            else -> {
                // no-op
            }
        }

    }
}
