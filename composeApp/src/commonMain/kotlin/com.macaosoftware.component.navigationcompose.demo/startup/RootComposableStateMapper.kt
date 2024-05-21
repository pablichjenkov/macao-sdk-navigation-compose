package com.macaosoftware.component.navigationcompose.demo.startup

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.macaosoftware.component.ComposableStateMapper
import com.macaosoftware.component.drawer.DrawerView
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.DrawerViewModelDefault
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
import com.macaosoftware.component.util.EmptyNavigationView
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

class RootComposableStateMapper(
    private val rootJsonObject: JsonObject,
) : ComposableStateMapper {

    override fun getInitialRoute(): String {
        val componentType: String =
            rootJsonObject
                .getValue(ServerUiConstants.JsonKeyName.componentType)
                .jsonPrimitive
                .content

        return componentType
    }

    @Composable
    override fun ContentForRoot(stateType: String) = MapComposableToState(stateType)

    @Composable
    override fun ContentForRoute(navHostController: NavHostController, navBackStackEntry: NavBackStackEntry) {
        TODO("Not yet implemented")
    }
}

@OptIn(KoinExperimentalAPI::class)
@Composable
private fun MapComposableToState(stateType: String) = when (stateType) {

    ServerUiConstants.ComponentType.Drawer -> {

        val viewModel = koinViewModel<DrawerViewModelDefault>()
        DrawerView(viewModel)
    }

    else -> {
        EmptyNavigationView()
    }
}
