package com.macaosoftware.component.navigationcompose.demo.serverui

import androidx.compose.runtime.Composable
import com.macaosoftware.component.core.RootDestinationRender
import com.macaosoftware.component.drawer.DrawerView
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.DrawerViewModelDefault
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
import com.macaosoftware.component.util.EmptyNavigationView
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.koin.compose.viewmodel.koinViewModel

class ComposeAppRootDestinationRender(
    rootJsonObject: JsonObject,
) : RootDestinationRender {

    private val initialRoute: String = rootJsonObject
        .getValue(ServerUiConstants.JsonKeyName.componentType)
        .jsonPrimitive
        .content

    @Composable
    override fun Content() = when (initialRoute) {

        ServerUiConstants.ComponentType.Drawer -> {
            val viewModel = koinViewModel<DrawerViewModelDefault>()
            DrawerView(viewModel)
        }

        else -> {
            EmptyNavigationView()
        }
    }

}
