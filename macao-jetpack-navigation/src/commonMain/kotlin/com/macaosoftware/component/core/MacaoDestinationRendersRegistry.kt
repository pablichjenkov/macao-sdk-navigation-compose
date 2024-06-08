package com.macaosoftware.component.core

import com.macaosoftware.component.drawer.DrawerResultAdapterEmpty
import com.macaosoftware.component.drawer.DrawerResultProcessor

class MacaoDestinationRendersRegistry(
    private val destinationRenderNotFound: DestinationRender,
    private val rootDestinationRenderNotFound: RootDestinationRender
) : DestinationRendersRegistry {

    private val allDestinationRenders = mutableListOf<DestinationRender>()
    private val allRootDestinationRenders = mutableListOf<RootDestinationRender>()
    private val allDrawerResultResultProcessors = mutableListOf<DrawerResultProcessor>()

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

    override fun addDrawerResultProcessor(
        drawerResultProcessor: DrawerResultProcessor
    ) {
        allDrawerResultResultProcessors.add(drawerResultProcessor)
    }

    override fun drawerResultProcessorFor(
        destinationType: String
    ): DrawerResultProcessor = allDrawerResultResultProcessors.first { render ->
        render.getRenderType() == destinationType
    }

}
