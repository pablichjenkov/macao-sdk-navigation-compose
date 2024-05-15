package com.macaosoftware.sdui.app.marketplace.navigator.bottomnavigation

import com.macaosoftware.component.bottomnavigation.BottomNavigationComponent
import com.macaosoftware.component.bottomnavigation.BottomNavigationComponentViewModelFactory
import com.macaosoftware.component.bottomnavigation.BottomNavigationStatePresenterDefault
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.bottomnavigation.BottomNavigationSduiHandler
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.bottomnavigation.BottomNavigationViewModel

class BottomNavigationViewModelFactory(
    private val sduiHandler: BottomNavigationSduiHandler,
    private val bottomNavigationStatePresenter: BottomNavigationStatePresenterDefault
) : BottomNavigationComponentViewModelFactory<BottomNavigationViewModel> {

    override fun create(
        bottomNavigationComponent: BottomNavigationComponent<BottomNavigationViewModel>
    ): BottomNavigationViewModel {
        return BottomNavigationViewModel(
            sduiHandler = sduiHandler,
            bottomNavigationComponent = bottomNavigationComponent,
            bottomNavigationStatePresenter = bottomNavigationStatePresenter
        )
    }
}
