package com.macaosoftware.component.navigationcompose.demo.marketplace

import com.macaosoftware.component.core.DestinationRender
import com.macaosoftware.component.core.DestinationRendersRegistry
import com.macaosoftware.component.core.RootDestinationRender
import com.macaosoftware.component.navigationcompose.demo.serverui.domain.error.DestinationRenderNotFound
import com.macaosoftware.component.navigationcompose.demo.serverui.domain.error.RootDestinationRenderNotFound

class DemoDestinationRendersRegistry : DestinationRendersRegistry {

    private val allDestinationRenders = mutableListOf<DestinationRender>()
    private val allRootDestinationRenders = mutableListOf<RootDestinationRender>()

    override fun add(destinationRender: DestinationRender) {
        allDestinationRenders.add(destinationRender)
    }

    override fun renderFor(
        renderType: String
    ): DestinationRender = allDestinationRenders.firstOrNull { render ->
        render.getRenderType() == renderType
    } ?: DestinationRenderNotFound()

    override fun addRoot(destinationRender: RootDestinationRender) {
        allRootDestinationRenders.add(destinationRender)
    }

    override fun renderForRoot(
        rootRenderType: String
    ): RootDestinationRender = allRootDestinationRenders.firstOrNull { render ->
        render.getRenderType() == rootRenderType
    } ?: RootDestinationRenderNotFound()

}
