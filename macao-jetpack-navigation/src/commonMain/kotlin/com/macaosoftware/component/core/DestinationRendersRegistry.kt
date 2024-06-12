package com.macaosoftware.component.core

import com.macaosoftware.component.drawer.DrawerResultAdapter
import com.macaosoftware.component.stack.StackResultAdapter

interface DestinationRendersRegistry {

    fun add(destinationRender: DestinationRender)
    fun renderFor(renderType: String): DestinationRender

    fun addRoot(destinationRender: RootDestinationRender)
    fun renderForRoot(rootRenderType: String): RootDestinationRender

    fun addDrawerResultAdapter(drawerResultAdapter:  DrawerResultAdapter<*>)
    fun drawerResultAdapterFor(
        destinationType: String
    ): DrawerResultAdapter<*>

    fun addStackResultAdapter(stackResultAdapter: StackResultAdapter<*>)
    fun stackResultAdapterFor(destinationType: String): StackResultAdapter<*>
}
