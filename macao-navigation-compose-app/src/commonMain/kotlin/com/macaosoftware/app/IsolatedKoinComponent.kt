package com.macaosoftware.app

import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent

internal class IsolatedKoinComponent(
    val koinApplication: KoinApplication
) : KoinComponent {

    override fun getKoin(): Koin = koinApplication.koin
}
