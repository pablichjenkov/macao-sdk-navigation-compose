package com.macaosoftware.component.navigationcompose.demo.serverui.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.navigationcompose.demo.serverui.domain.MacaoApiError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.putJsonArray

internal class ServerUiRemoteService(
    private val httpClient: HttpClient
) {

    fun getRootJsonResilience() = buildJsonObject {
        put(
            ServerUiConstants.JsonKeyName.route,
            JsonPrimitive(ServerUiConstants.Routes.RootGraph.MainEntryPoint)
        )
        put(
            ServerUiConstants.JsonKeyName.componentType,
            JsonPrimitive(ServerUiConstants.ComponentType.Drawer)
        )
        putJsonArray(ServerUiConstants.JsonKeyName.children) {
            addJsonObject {
                put(
                    ServerUiConstants.JsonKeyName.route,
                    JsonPrimitive(ServerUiConstants.Routes.RootGraph.SimpleScreen)
                )
                put(
                    ServerUiConstants.JsonKeyName.componentType,
                    JsonPrimitive(ServerUiConstants.ComponentType.SimpleScreen)
                )
            }
            addJsonObject {
                put(
                    ServerUiConstants.JsonKeyName.route,
                    JsonPrimitive(ServerUiConstants.Routes.RootGraph.SimpleScreen1)
                )
                put(
                    ServerUiConstants.JsonKeyName.componentType,
                    JsonPrimitive(ServerUiConstants.ComponentType.SimpleScreen1)
                )
            }
        }
    }

    suspend fun getRemoteRootComponent(ownerId: String): JsonObject? {
        // val baseUrl = "http://localhost:8080"
        val baseUrl = "https://ktor-gae-401000.appspot.com"
        val resp = httpClient.get(
            urlString = "${baseUrl}/customer-project/json-data/${ownerId}"
        )
        return if (resp.status.isSuccess()) {
            val bodyAsText = resp.bodyAsText()
            println("bodyText = $bodyAsText")
            val jsonObject = Json.decodeFromString<JsonObject>(bodyAsText)
            jsonObject
        } else {
            val macaoError = resp.body<MacaoApiError>()
            println("macaoError = $macaoError")
            null
        }
    }

    suspend fun getRootDestinationInfo(): DestinationInfo {

        // TODO: Remove this hard code
        delay(800)

        return DestinationInfo(
            route = ServerUiConstants.Routes.RootGraph.MainEntryPoint,
            renderType = ServerUiConstants.ComponentType.Drawer,
            dataSource = "https://ktor-gae-401000.appspot.com/customer-project/json-data/${123}",

            // Presentation
            label = "Label: Drawer",
            icon = Icons.Default.Menu,
            badgeText = "0"
        )
    }
}
