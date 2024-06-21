package com.macaosoftware.app.startup.task

import com.macaosoftware.app.InitializationError

class StartupTaskError(
    val errorMsg: String
) : InitializationError