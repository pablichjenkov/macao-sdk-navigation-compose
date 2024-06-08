package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen.ui

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
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen.SimpleScreenResultV2
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
import com.macaosoftware.util.MacaoResult
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf

class SimpleScreenDestinationRender : DestinationRender {

    override fun getRenderType(): String {
        return ServerUiConstants.ComponentType.SimpleScreen
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
        /*val viewModel1 = getKoin().get<SimpleScreenViewModel>(
            parameters = {
                parametersOf(
                    Color.Blue,
                    resultHandler
                )
            }
        )*/

        /**
         * Don't use this one. It just create a dependency but is
         * scoped to the lifecycle of a `remember block` in this composable.
         * */
        /*val viewModel2 = koinInject<SimpleScreenViewModel>(
            parameters = {
                parametersOf(
                    Color.Blue,
                    resultHandler
                )
            }
        )*/

        /**
         * YES use this one. It scopes the VM to the nearest ViewModelStoreOwner
         * and creates the VM from the parent scope Local_KoinScope dependencies
         * */
        val viewModel3 = koinViewModel<SimpleScreenViewModel>( // Or koinNavViewModel, also good
            parameters = {
                parametersOf(
                    Color.Blue
                )
            }
        )

        SimpleScreenView(
            viewModel = viewModel3,
            resultAdapter = resultAdapter
        )
    }

    override fun getDrawerResultProcessor(
        drawer: DrawerStatePresenter,
        navController: NavHostController
    ): ResultProcessor {

        return object : ResultProcessor {

            override fun getRenderType(): String {
                return ServerUiConstants.ComponentType.SimpleScreen
            }

            override fun process(destinationResultV2: DestinationResultV2) {

                    when (destinationResultV2) {
                        CancelV2 -> navController.popBackStack()

                        is SimpleScreenResultV2.Success -> {
                            drawer.setDrawerState(DrawerValue.Open)
                            println("SimpleScreen returned: ${destinationResultV2.value}")
                        }

                        is SimpleScreenResultV2.Error -> {
                            println("SimpleScreen returned: ${destinationResultV2.error}")
                        }

                        else -> {
                            // no-op
                        }
                    }
                }

            }
    }

}
