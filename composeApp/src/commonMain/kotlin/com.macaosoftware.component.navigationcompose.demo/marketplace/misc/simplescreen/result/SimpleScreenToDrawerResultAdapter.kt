package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen.result

import androidx.compose.material3.DrawerValue
import com.macaosoftware.component.core.DestinationResult
import com.macaosoftware.component.drawer.DrawerResultAdapter
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.result.SimpleScreen1Result
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants

class SimpleScreenToDrawerResultAdapter
    : DrawerResultAdapter<SimpleScreen1Result>() {

    override fun getRenderType(): String {
        return ServerUiConstants.ComponentType.SimpleScreen
    }

    override fun process(result: DestinationResult<SimpleScreen1Result>) {

        when (result) {

            is DestinationResult.Error -> {
                println("SimpleScreen returned error: ${result.error}")
                navController.popBackStack()
            }

            is DestinationResult.Success -> {
                drawerStatePresenter.setDrawerState(DrawerValue.Open)
                println("SimpleScreen returned success: ${result.value}")
            }
        }

    }
}
