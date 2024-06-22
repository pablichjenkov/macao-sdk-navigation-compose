package com.macaosoftware.component.stack

import androidx.navigation.NavHostController
import com.macaosoftware.component.core.DestinationResult
import com.macaosoftware.component.core.ResultHandler

abstract class StackResultHandler<T> : ResultHandler<DestinationResult<T>> {

    lateinit var stackStatePresenter: StackStatePresenter
    lateinit var navController: NavHostController
}
