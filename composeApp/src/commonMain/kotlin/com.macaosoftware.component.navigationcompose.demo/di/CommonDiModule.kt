package com.macaosoftware.component.navigationcompose.demo.di

import com.macaosoftware.component.navigationcompose.demo.serverui.data.ServerUiRemoteService
import com.macaosoftware.component.navigationcompose.demo.di.http.createDefaultHttpClient
import io.ktor.client.HttpClient
import kotlinx.datetime.Clock
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val commonKoinModule = module {
    single<Clock> {
        Clock.System
    }
    single<HttpClient> {
        createDefaultHttpClient()
    }
    singleOf(::ServerUiRemoteService)
}
