package com.macaosoftware.component.core

interface DestinationRendersRegistry {

    fun add(destinationRender: DestinationRender)
    fun renderFor(renderType: String): DestinationRender

    // TODO Experimental
    fun addRoot(destinationRender: RootDestinationRender)
    fun renderForRoot(rootRenderType: String): RootDestinationRender
}
