package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.result

import androidx.compose.material3.DrawerValue
import com.macaosoftware.component.core.DestinationResult
import com.macaosoftware.component.drawer.DrawerResultHandler
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants

class SimpleScreen1ToDrawerResultHandler
    : DrawerResultHandler<SimpleScreen1Result>() {

    override fun getRenderType(): String {
        return ServerUiConstants.ComponentType.SimpleScreen1
    }

    override fun process(result: DestinationResult<SimpleScreen1Result>) {

        when (result) {

            is DestinationResult.Error -> {
                println("SimpleScreen1 returned: ${result.error}")
                navController.popBackStack()
            }

            is DestinationResult.Success -> {
                println("SimpleScreen1 returned: ${result.value}")
                drawerStatePresenter.setDrawerState(DrawerValue.Open)
            }
        }

    }
}
