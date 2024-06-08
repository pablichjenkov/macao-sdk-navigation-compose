package com.macaosoftware.component.core

interface ResultProcessor {

    fun getRenderType(): String
    fun process(destinationResultV2: DestinationResultV2)
}
