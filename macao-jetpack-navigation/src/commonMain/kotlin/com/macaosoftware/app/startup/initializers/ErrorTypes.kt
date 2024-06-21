package com.macaosoftware.app.startup.initializers

import com.macaosoftware.app.InitializationError

class RootGraphInitializerError(
    val errorMsg: String
) : InitializationError
