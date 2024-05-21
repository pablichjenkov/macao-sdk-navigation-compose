package com.macaosoftware.component.navigationcompose.demo.di

import com.macaosoftware.component.drawer.DrawerComponentDefaults
import com.macaosoftware.component.drawer.DrawerStatePresenterDefault
import com.macaosoftware.component.navigationcompose.demo.marketplace.misc.SimpleScreenViewModel
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.DrawerSduiHandler
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.DrawerViewModelDefault
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiRemoteService
import com.macaosoftware.component.navigationcompose.demo.serverui.domain.ServerUiJsonToComponentTypeMapper
import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiConstants
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.putJsonArray
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal val macaoViewModelsModule = module {

    factory<ServerUiJsonToComponentTypeMapper> {
        ServerUiJsonToComponentTypeMapper()
    }
    factory<DrawerSduiHandler> {
        DrawerSduiHandler(
            getRootJsonResilience(), // It should be a parameter
            get()
        )
    }
    factory <DrawerStatePresenterDefault> {
        DrawerComponentDefaults.createDrawerStatePresenter()
    }
    viewModelOf(::DrawerViewModelDefault) {}

    factory<SimpleScreenViewModel> { params ->
        SimpleScreenViewModel(
            bgColor = params.get(),
            resultHandler = params.get()
        )
    }

}

private fun getRootJsonResilience() = buildJsonObject {
    put(
        ServerUiConstants.JsonKeyName.componentType,
        JsonPrimitive(ServerUiRemoteService.RootComponent)
    )
    putJsonArray(ServerUiConstants.JsonKeyName.children) {
        addJsonObject {
            put(
                ServerUiConstants.JsonKeyName.componentType,
                JsonPrimitive(ServerUiConstants.ComponentType.SimpleScreen)
            )
        }
        addJsonObject {
            put(
                ServerUiConstants.JsonKeyName.componentType,
                JsonPrimitive(ServerUiConstants.ComponentType.SimpleScreen1)
            )
        }
    }
}
