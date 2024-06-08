package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen.result

import androidx.compose.material3.DrawerValue
import com.macaosoftware.component.core.DestinationResult
import com.macaosoftware.component.drawer.DrawerResultProcessor
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants

class SimpleScreenToDrawerResultProcessor
    : DrawerResultProcessor() {

    override fun getRenderType(): String {
        return ServerUiConstants.ComponentType.SimpleScreen
    }

    override fun process(result: DestinationResult) {

        if (result !is SimpleScreenResult) return

        when (result) {

            is SimpleScreenResult.Error -> {
                println("SimpleScreen returned error: ${result.error}")
                navController.popBackStack()
            }

            is SimpleScreenResult.Success -> {
                drawerStatePresenter.setDrawerState(DrawerValue.Open)
                println("SimpleScreen returned success: ${result.value}")
            }
        }

    }
}
