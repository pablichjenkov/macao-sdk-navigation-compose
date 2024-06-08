package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.ui

import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
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
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.SimpleScreen1Result
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.SimpleScreen1ResultV2
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
import com.macaosoftware.util.MacaoResult
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf

class SimpleScreen1DestinationRender : DestinationRender {

    override fun getRenderType(): String {
        return ServerUiConstants.ComponentType.SimpleScreen1
    }

    @OptIn(KoinExperimentalAPI::class)
    @Composable
    override fun Content(
        destinationInfo: DestinationInfo,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        resultAdapter: ResultAdapter<DestinationResult<*>>
    ) {

        /**
         * Don't use this one. It just create a dependency but is
         * not scoped. It just create the dependency from the global
         * LocalKoinApplication.current, which is the root koin scope.
         * */
        val viewModel = koinViewModel<SimpleScreen1ViewModel>(
            parameters = {
                parametersOf(
                    Color.Green
                )
            }
        )

        SimpleScreen1View(
            viewModel = viewModel,
            resultAdapter = resultAdapter
        )
    }

    override fun getDrawerResultProcessor(
        drawer: DrawerStatePresenter,
        navController: NavHostController
    ): ResultProcessor {

        return object : ResultProcessor {

            override fun getRenderType(): String {
                return ServerUiConstants.ComponentType.SimpleScreen1
            }

            override fun process(destinationResultV2: DestinationResultV2) {
                when (destinationResultV2) {
                    CancelV2 -> navController.popBackStack()

                    is SimpleScreen1ResultV2.Success -> {
                        println("SimpleScreen1 returned: ${destinationResultV2.value}")
                    }

                    is SimpleScreen1ResultV2.Error -> {
                        println("SimpleScreen1 returned: ${destinationResultV2.error}")
                        drawer.setDrawerState(DrawerValue.Open)
                        navController.popBackStack()
                    }
                    else -> {
                        // no-op
                    }
                }
            }

        }
    }

}
