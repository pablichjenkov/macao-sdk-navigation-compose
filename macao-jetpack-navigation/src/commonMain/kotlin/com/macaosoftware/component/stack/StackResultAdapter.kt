package com.macaosoftware.component.stack

import androidx.navigation.NavHostController
import com.macaosoftware.component.core.DestinationResult
import com.macaosoftware.component.core.ResultAdapter

abstract class StackResultAdapter<T> : ResultAdapter<DestinationResult<T>> {

    lateinit var stackStatePresenter: StackStatePresenter
    lateinit var navController: NavHostController
}
