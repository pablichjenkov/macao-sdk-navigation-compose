package com.macaosoftware.component.core

interface ResultHandler<in T : DestinationResult<*>> {

    fun getRenderType(): String
    fun process(result: T)
}
