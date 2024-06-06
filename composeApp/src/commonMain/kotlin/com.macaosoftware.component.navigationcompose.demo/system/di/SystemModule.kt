package com.macaosoftware.component.navigationcompose.demo.system.di

import com.macaosoftware.component.navigationcompose.demo.serverui.http.createDefaultHttpClient
import io.ktor.client.HttpClient
import kotlinx.datetime.Clock
import org.koin.dsl.module

internal val commonKoinModule = module {
    single<Clock> {
        Clock.System
    }
    single<HttpClient> {
        createDefaultHttpClient()
    }
}
