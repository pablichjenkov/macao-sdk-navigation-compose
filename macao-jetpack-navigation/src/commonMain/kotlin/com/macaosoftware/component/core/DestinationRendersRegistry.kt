package com.macaosoftware.component.core

import com.macaosoftware.component.drawer.DrawerResultHandler
import com.macaosoftware.component.stack.StackResultHandler

interface DestinationRendersRegistry {

    fun add(destinationRender: DestinationRender)
    fun renderFor(renderType: String): DestinationRender

    fun addRoot(destinationRender: RootDestinationRender)
    fun renderForRoot(rootRenderType: String): RootDestinationRender

    fun addDrawerResultAdapter(drawerResultAdapter:  DrawerResultHandler<*>)
    fun drawerResultAdapterFor(
        destinationType: String
    ): DrawerResultHandler<*>

    fun addStackResultAdapter(stackResultAdapter: StackResultHandler<*>)
    fun stackResultAdapterFor(destinationType: String): StackResultHandler<*>
}
