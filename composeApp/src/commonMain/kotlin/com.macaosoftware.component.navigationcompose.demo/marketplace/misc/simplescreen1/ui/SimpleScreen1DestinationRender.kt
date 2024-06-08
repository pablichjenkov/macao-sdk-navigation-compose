package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.core.DestinationRender
import com.macaosoftware.component.core.DestinationResult
import com.macaosoftware.component.core.ResultAdapter
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
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
}
