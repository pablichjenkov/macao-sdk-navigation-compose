package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.result

import androidx.compose.material3.DrawerValue
import com.macaosoftware.component.core.DestinationResult
import com.macaosoftware.component.drawer.DrawerResultProcessor
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants

class SimpleScreen1ToDrawerResultProcessor
    : DrawerResultProcessor() {

    override fun getRenderType(): String {
        return ServerUiConstants.ComponentType.SimpleScreen1
    }

    override fun process(result: DestinationResult) {

        if (result !is SimpleScreen1Result) return

        when (result) {

            is SimpleScreen1Result.Error -> {
                println("SimpleScreen1 returned: ${result.error}")
                navController.popBackStack()
            }

            is SimpleScreen1Result.Success -> {
                println("SimpleScreen1 returned: ${result.value}")
                drawerStatePresenter.setDrawerState(DrawerValue.Open)
            }

        }

    }
}
