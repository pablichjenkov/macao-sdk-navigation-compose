package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1

import androidx.compose.material3.DrawerValue
import com.macaosoftware.component.core.DestinationResult
import com.macaosoftware.component.drawer.DrawerResultAdapter
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants

class SimpleScreen1ToDrawerResultAdapter
    : DrawerResultAdapter<SimpleScreen1Result>() {

    override fun getRenderType(): String {
        return ServerUiConstants.ComponentType.SimpleScreen1
    }

    override fun process(result: DestinationResult<SimpleScreen1Result>) {

        when (result) {

            is DestinationResult.Error -> {
                println("SimpleScreen1 returned: ${result.error}")
                drawerStatePresenter.setDrawerState(DrawerValue.Open)
                navController.popBackStack()
            }

            is DestinationResult.Success -> {
                println("SimpleScreen1 returned: ${result.value}")
            }
        }

    }
}
