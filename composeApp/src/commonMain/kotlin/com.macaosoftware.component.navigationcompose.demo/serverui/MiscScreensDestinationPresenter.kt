package com.macaosoftware.component.navigationcompose.demo.serverui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.macaosoftware.component.core.DestinationPresenter
import com.macaosoftware.component.core.NavItem
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.SimpleScreenView
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.SimpleScreenViewModel
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.DrawerViewModelDefault
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
import com.macaosoftware.component.util.EmptyNavigationView
import org.koin.compose.getKoin
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf

class MiscScreensDestinationPresenter : DestinationPresenter {

    override fun getRoute(): String {
        TODO("Not yet implemented")
    }

    override fun getNavItem(): NavItem {
        TODO("Not yet implemented")
    }

    @OptIn(KoinExperimentalAPI::class)
    @Composable
    override fun Content(
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry
    ) = when (navBackStackEntry.destination.route) {

        ServerUiConstants.ComponentType.SimpleScreen -> {

            // Create backstack entry from parent route which was passed in NavHost
            val parentBackStackEntry = remember { navController.getBackStackEntry(navBackStackEntry.destination.parent!!.route!!) }
            // pass the backstack entry as viewModelStoreOwner
            val drawerViewModel = koinViewModel<DrawerViewModelDefault>(viewModelStoreOwner = parentBackStackEntry)

            val resultHandler = { result: SimpleScreenViewModel.Result ->
                drawerViewModel.handleBackPressed()
                navController.popBackStack()
            }

            val viewModel = getKoin().get<SimpleScreenViewModel>(
                parameters = {
                    parametersOf(
                        Color.Blue,
                        resultHandler
                    )
                }
            )

            SimpleScreenView(viewModel)
        }

        ServerUiConstants.ComponentType.SimpleScreen1 -> {

            val parentBackStackEntry = remember { navController.getBackStackEntry(navBackStackEntry.destination.parent!!.route!!) }
            val drawerViewModel = koinViewModel<DrawerViewModelDefault>(viewModelStoreOwner = parentBackStackEntry)

            val resultHandler = { result: SimpleScreenViewModel.Result ->
                drawerViewModel.handleBackPressed()
                navController.popBackStack()
            }

            val viewModel = getKoin().get<SimpleScreenViewModel>(
                parameters = {
                    parametersOf(
                        Color.Green,
                        resultHandler
                    )
                }
            )

            SimpleScreenView(viewModel)
        }

        else -> {
            EmptyNavigationView()
        }
    }
}
