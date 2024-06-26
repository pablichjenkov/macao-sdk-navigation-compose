package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.core.DestinationRender
import com.macaosoftware.component.core.DestinationResult
import com.macaosoftware.component.core.ResultHandler
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
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
        resultHandler: ResultHandler<DestinationResult<*>>
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
            resultHandler = resultHandler
        )

        // todo: Remove this
        // val viewModel4 = koinViewModel<AnnoViewModel>()
    }
}
