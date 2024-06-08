package com.macaosoftware.component.core

import com.macaosoftware.component.drawer.DrawerResultAdapter
import com.macaosoftware.component.drawer.DrawerResultAdapterEmpty

class MacaoDestinationRendersRegistry(
    private val destinationRenderNotFound: DestinationRender,
    private val rootDestinationRenderNotFound: RootDestinationRender
) : DestinationRendersRegistry {

    private val allDestinationRenders = mutableListOf<DestinationRender>()
    private val allRootDestinationRenders = mutableListOf<RootDestinationRender>()
    private val allResultAdapters = mutableListOf<DrawerResultAdapter<*>>()

    override fun add(destinationRender: DestinationRender) {
        allDestinationRenders.add(destinationRender)
    }

    override fun renderFor(
        renderType: String
    ): DestinationRender = allDestinationRenders.firstOrNull { render ->
        render.getRenderType() == renderType
    } ?: destinationRenderNotFound

    override fun addRoot(destinationRender: RootDestinationRender) {
        allRootDestinationRenders.add(destinationRender)
    }

    override fun renderForRoot(
        rootRenderType: String
    ): RootDestinationRender = allRootDestinationRenders.firstOrNull { render ->
        render.getRenderType() == rootRenderType
    } ?: rootDestinationRenderNotFound

    override fun addDrawerResultAdapter(
        drawerResultAdapter: DrawerResultAdapter<*>
    ) {
        allResultAdapters.add(drawerResultAdapter)
    }

    override fun drawerResultAdapterFor(
        destinationType: String
    ): DrawerResultAdapter<*> = allResultAdapters.firstOrNull { render ->
        render.getRenderType() == destinationType
    } ?: DrawerResultAdapterEmpty()

}
