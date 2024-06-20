package com.macaosoftware.plugin

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

actual fun getCoroutineDispatchers() = CoroutineDispatchers(
    main = Dispatchers.Main,
    default = Dispatchers.Default,
    io = Dispatchers.IO
)
