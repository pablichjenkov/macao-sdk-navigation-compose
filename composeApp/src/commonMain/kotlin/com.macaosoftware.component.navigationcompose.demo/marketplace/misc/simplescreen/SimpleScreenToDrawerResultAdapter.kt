package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen

import androidx.compose.material3.DrawerValue
import com.macaosoftware.component.core.DestinationResult
import com.macaosoftware.component.drawer.DrawerResultAdapter
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.SimpleScreen1ResultV2
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants

class SimpleScreenToDrawerResultAdapter
    : DrawerResultAdapter<SimpleScreen1ResultV2>() {

    override fun getRenderType(): String {
        return ServerUiConstants.ComponentType.SimpleScreen
    }

    override fun process(result: DestinationResult<SimpleScreen1ResultV2>) {

        when (result) {

            is DestinationResult.Error -> {
                navController.popBackStack()
                println("SimpleScreen returned error: ${result.error}")
            }

            is DestinationResult.Success -> {
                drawerStatePresenter.setDrawerState(DrawerValue.Open)
                println("SimpleScreen returned success: ${result.value}")
            }
        }

    }
}
