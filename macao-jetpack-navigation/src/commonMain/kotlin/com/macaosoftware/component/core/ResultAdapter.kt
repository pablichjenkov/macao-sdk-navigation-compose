package com.macaosoftware.component.core

interface ResultAdapter<in T : DestinationResult<*>> {

    fun getRenderType(): String
    fun process(result: T)
}
