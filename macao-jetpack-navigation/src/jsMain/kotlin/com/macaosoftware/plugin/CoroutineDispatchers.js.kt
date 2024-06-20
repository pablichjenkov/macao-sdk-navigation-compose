package com.macaosoftware.plugin

import kotlinx.coroutines.Dispatchers

actual fun getCoroutineDispatchers() = CoroutineDispatchers(
    main = Dispatchers.Main,
    default = Dispatchers.Default,
    io = Dispatchers.Unconfined
)
