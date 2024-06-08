package com.macaosoftware.component.core

import com.macaosoftware.component.drawer.DrawerResultAdapter

interface DestinationRendersRegistry {

    fun add(destinationRender: DestinationRender)
    fun renderFor(renderType: String): DestinationRender

    // TODO: Experimental
    fun addRoot(destinationRender: RootDestinationRender)
    fun renderForRoot(rootRenderType: String): RootDestinationRender

    fun addDrawerResultAdapter(drawerResultAdapter:  DrawerResultAdapter<*>)
    fun drawerResultAdapterFor(
        destinationType: String
    ): DrawerResultAdapter<*>
}
