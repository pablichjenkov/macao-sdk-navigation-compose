package com.macaosoftware.component.core

import com.macaosoftware.component.drawer.DrawerResultHandler
import com.macaosoftware.component.drawer.DrawerResultHandlerEmpty
import com.macaosoftware.component.stack.StackResultHandler

class MacaoDestinationRendersRegistry(
    private val destinationRenderNotFound: DestinationRender,
    private val rootDestinationRenderNotFound: RootDestinationRender
) : DestinationRendersRegistry {

    /**
     * Destination Renders
     * */
    private val allDestinationRenders = mutableListOf<DestinationRender>()

    override fun add(destinationRender: DestinationRender) {
        allDestinationRenders.add(destinationRender)
    }

    override fun renderFor(
        renderType: String
    ): DestinationRender = allDestinationRenders.firstOrNull { render ->
        render.getRenderType() == renderType
    } ?: destinationRenderNotFound

    //**********************************************//

    /**
     * Root Destination Renders
     * */
    private val allRootDestinationRenders = mutableListOf<RootDestinationRender>()

    override fun addRoot(destinationRender: RootDestinationRender) {
        allRootDestinationRenders.add(destinationRender)
    }

    override fun renderForRoot(
        rootRenderType: String
    ): RootDestinationRender = allRootDestinationRenders.firstOrNull { render ->
        render.getRenderType() == rootRenderType
    } ?: rootDestinationRenderNotFound

    //**********************************************//

    /**
     * DrawerResultAdapter
     * */
    private val allDrawerResultAdapters = mutableListOf<DrawerResultHandler<*>>()

    override fun addDrawerResultAdapter(
        drawerResultAdapter: DrawerResultHandler<*>
    ) {
        allDrawerResultAdapters.add(drawerResultAdapter)
    }

    override fun drawerResultAdapterFor(
        destinationType: String
    ): DrawerResultHandler<*> = allDrawerResultAdapters.firstOrNull { render ->
        render.getRenderType() == destinationType
    } ?: DrawerResultHandlerEmpty()

    //**********************************************//

    /**
     * StackResultAdapter
     * */
    private val allStackResultAdapters = mutableListOf<StackResultHandler<*>>()

    override fun addStackResultAdapter(stackResultAdapter: StackResultHandler<*>) {
        allStackResultAdapters.add(stackResultAdapter)
    }

    override fun stackResultAdapterFor(destinationType: String): StackResultHandler<*> {
        TODO("Not yet implemented")
    }

    //**********************************************//
}
