package com.macaosoftware.component.stack

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.core.DestinationRendersRegistry
import com.macaosoftware.component.core.DestinationResult
import com.macaosoftware.component.core.ResultHandler
import com.macaosoftware.component.util.BackPressHandler

@Composable
fun StackView(
    destinationInfo: DestinationInfo,
    viewModel: StackViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val destinations = viewModel.stackStatePresenter.navItemsState.value
    val showFloatingButton = viewModel.stackStatePresenter.showFloatingButton.value

    BackPressHandler {
        viewModel.handleBackPressed()
    }

    LifecycleStartEffect(key1 = destinationInfo) {

        viewModel.onStart(destinationInfo)

        onStopOrDispose { viewModel.onStop() }
    }

    if (destinations.isNotEmpty()) {
        StackNavigation(
            modifier = modifier,
            statePresenter = viewModel.stackStatePresenter,
            navController = navController,
            destinationRendersRegistry = viewModel.destinationRendersRegistry,
            destinations = destinations
        )
    } else {
        StackEmptyView(destinationInfo.route)
    }
}

@Composable
private fun StackNavigation(
    modifier: Modifier,
    statePresenter: StackStatePresenter,
    navController: NavHostController,
    destinationRendersRegistry: DestinationRendersRegistry,
    destinations: List<StackNavItem>,
) {

    NavHost(
        navController = navController,
        route = "Component-Stack",
        startDestination = destinations[0].destinationInfo.route,
        modifier = modifier.fillMaxSize()
    ) {
        destinations.forEach { destination ->
            composable(destination.destinationInfo.route) { backstackEntry ->

                val destinationRender = destinationRendersRegistry
                    .renderFor(destination.destinationInfo.renderType)

                val resultAdapter = destinationRendersRegistry
                    .stackResultAdapterFor(
                        destination.destinationInfo.renderType
                    ).apply {
                        this.stackStatePresenter = statePresenter
                        this.navController = navController
                    }

                destinationRender.Content(
                    destination.destinationInfo,
                    navController,
                    backstackEntry,
                    resultAdapter as ResultHandler<DestinationResult<*>>
                )
            }
        }
    }
}
