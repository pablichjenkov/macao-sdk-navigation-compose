package com.macaosoftware.component.navigationcompose.demo.marketplace.notfound.result

import com.macaosoftware.component.core.DestinationResult
import com.macaosoftware.component.drawer.DrawerResultHandler
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.result.SimpleScreen1Result
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants

class NotFoundToDrawerResultHandler
    : DrawerResultHandler<SimpleScreen1Result>() {

    override fun getRenderType(): String {
        return ServerUiConstants.ComponentType.DestinationNotFound
    }

    override fun process(result: DestinationResult<SimpleScreen1Result>) {

        when (result) {

            is DestinationResult.Error -> {
                navController.popBackStack()
            }

            is DestinationResult.Success -> {
                navController.popBackStack()
            }
        }

    }
}
