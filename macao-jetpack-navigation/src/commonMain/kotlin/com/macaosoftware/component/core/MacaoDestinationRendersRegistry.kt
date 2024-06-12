package com.macaosoftware.component.core

import com.macaosoftware.component.drawer.DrawerResultAdapter
import com.macaosoftware.component.drawer.DrawerResultAdapterEmpty
import com.macaosoftware.component.stack.StackResultAdapter

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
    private val allDrawerResultAdapters = mutableListOf<DrawerResultAdapter<*>>()

    override fun addDrawerResultAdapter(
        drawerResultAdapter: DrawerResultAdapter<*>
    ) {
        allDrawerResultAdapters.add(drawerResultAdapter)
    }

    override fun drawerResultAdapterFor(
        destinationType: String
    ): DrawerResultAdapter<*> = allDrawerResultAdapters.firstOrNull { render ->
        render.getRenderType() == destinationType
    } ?: DrawerResultAdapterEmpty()

    //**********************************************//

    /**
     * StackResultAdapter
     * */
    private val allStackResultAdapters = mutableListOf<StackResultAdapter<*>>()

    override fun addStackResultAdapter(stackResultAdapter: StackResultAdapter<*>) {
        allStackResultAdapters.add(stackResultAdapter)
    }

    override fun stackResultAdapterFor(destinationType: String): StackResultAdapter<*> {
        TODO("Not yet implemented")
    }

    //**********************************************//
}
