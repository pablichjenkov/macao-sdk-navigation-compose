package com.macaosoftware.component.navigationcompose.demo.marketplace.notfound

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.macaosoftware.component.core.Cancel
import com.macaosoftware.component.core.CancelV2
import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.core.DestinationRender
import com.macaosoftware.component.core.DestinationResult
import com.macaosoftware.component.core.DestinationResultV2
import com.macaosoftware.component.core.ResultAdapter
import com.macaosoftware.component.core.ResultProcessor
import com.macaosoftware.component.drawer.DrawerStatePresenter
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
import com.macaosoftware.component.util.BackPressHandler
import com.macaosoftware.component.util.MacaoDestinationRenderNotFoundView

class DestinationRenderNotFound : DestinationRender {

    override fun getRenderType(): String {
        return ServerUiConstants.ComponentType.DestinationNotFound
    }

    @Composable
    override fun Content(
        destinationInfo: DestinationInfo,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        resultAdapter: ResultAdapter<DestinationResult<*>>
    ) {
        BackPressHandler {
            resultAdapter.process(DestinationResult.Error(Cancel))
        }
        MacaoDestinationRenderNotFoundView(destinationInfo.renderType)
    }

    override fun getDrawerResultProcessor(
        drawer: DrawerStatePresenter,
        navController: NavHostController
    ): ResultProcessor {

        return object : ResultProcessor {

            override fun getRenderType(): String {
                TODO("Not yet implemented")
            }

            override fun process(destinationResultV2: DestinationResultV2) {
                when (destinationResultV2) {
                    CancelV2 -> navController.popBackStack()

                    else -> {
                        // no-op
                    }
                }
            }

        }
    }
}
