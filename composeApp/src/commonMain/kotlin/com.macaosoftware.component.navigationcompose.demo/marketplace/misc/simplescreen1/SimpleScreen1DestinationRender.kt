package com.macaosoftware.component.navigationcompose.demo.marketplace.misc.simplescreen1

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.core.DestinationRender
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.DemoDrawerViewModel
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
import org.koin.compose.getKoin
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
        navBackStackEntry: NavBackStackEntry
    ) {

        val parentBackStackEntry = remember { navController.getBackStackEntry(navBackStackEntry.destination.parent!!.route!!) }
        val drawerViewModel = koinViewModel<DemoDrawerViewModel>(viewModelStoreOwner = parentBackStackEntry)

        val resultHandler = { result: SimpleScreen1ViewModel.Result ->
            drawerViewModel.handleBackPressed()
            navController.popBackStack()
        }

        /**
         * Don't use this one. It just create a dependency but is
         * not scoped. It just create the dependency from the global
         * LocalKoinApplication.current, which is the root koin scope.
         * */
        val viewModel = getKoin().get<SimpleScreen1ViewModel>(
            parameters = {
                parametersOf(
                    Color.Green,
                    resultHandler
                )
            }
        )

        SimpleScreen1View(viewModel)
    }

}
