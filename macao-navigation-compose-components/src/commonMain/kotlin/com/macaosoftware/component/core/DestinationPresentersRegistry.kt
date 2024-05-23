package com.macaosoftware.component.core

class DestinationPresentersRegistry {

    private val allDestinationPresenters = mutableListOf<DestinationPresenter>()

    fun add(destinationPresenter: DestinationPresenter) {
        allDestinationPresenters.add(destinationPresenter)
    }

    fun get(route: String): DestinationPresenter? = allDestinationPresenters.firstOrNull { destinationPresenter ->
        destinationPresenter.getRoute() == route
    }

}
