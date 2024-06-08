package com.macaosoftware.component.core

import com.macaosoftware.component.drawer.DrawerResultProcessor

interface DestinationRendersRegistry {

    fun add(destinationRender: DestinationRender)
    fun renderFor(renderType: String): DestinationRender

    // TODO: Experimental
    fun addRoot(destinationRender: RootDestinationRender)
    fun renderForRoot(rootRenderType: String): RootDestinationRender

    fun addDrawerResultProcessor(drawerResultProcessor:  DrawerResultProcessor)
    fun drawerResultProcessorFor(
        destinationType: String
    ): DrawerResultProcessor
}
