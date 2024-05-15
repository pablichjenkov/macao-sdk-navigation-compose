package com.macaosoftware.component.navigationcompose.demo.serverui.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.sharp.DateRange
import androidx.compose.material.icons.sharp.List
import com.macaosoftware.component.bottomnavigation.BottomNavigationComponent
import com.macaosoftware.component.bottomnavigation.BottomNavigationComponentDefaults
import com.macaosoftware.component.core.Component
import com.macaosoftware.component.core.NavItem
import com.macaosoftware.component.drawer.DrawerComponent
import com.macaosoftware.component.drawer.DrawerComponentDefaults
import com.macaosoftware.component.navigationcompose.demo.marketplace.auth.AuthViewModel
import com.macaosoftware.component.navigationcompose.demo.marketplace.auth.AuthViewModelFactory
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.bottomnavigation.BottomNavigationSduiHandler
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.drawer.DrawerSduiHandler
import com.macaosoftware.component.navigationcompose.demo.serverui.domain.error.ComponentMissingImplementation
import com.macaosoftware.component.panel.PanelComponent
import com.macaosoftware.component.panel.PanelComponentDefaults
import com.macaosoftware.component.stack.StackComponent
import com.macaosoftware.component.stack.StackComponentDefaults
import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.sdui.app.marketplace.navigator.bottomnavigation.BottomNavigationViewModelFactory
import com.macaosoftware.sdui.app.marketplace.navigator.drawer.DrawerViewModelFactory
import com.macaosoftware.component.navigationcompose.demo.marketplace.navigators.panel.PanelSduiHandler
import com.macaosoftware.sdui.app.marketplace.navigator.panel.panelfactory.PanelViewModelFactory
import com.macaosoftware.sdui.data.SduiConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class SduiComponentFactory(
    private val koinComponent: KoinComponent
) : MacaoComponentFactory, KoinComponent by koinComponent {

    override fun getNavItemOf(
        componentJson: JsonObject
    ): NavItem {
        val childComponentType: String =
            componentJson
                .getValue(SduiConstants.JsonKeyName.componentType)
                .jsonPrimitive
                .content

        return when (childComponentType) {

            SduiConstants.ComponentType.BottomNavigation -> {
                NavItem(
                    label = SduiConstants.ComponentType.BottomNavigation,
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Default.AccountBox
                )
            }

            SduiConstants.ComponentType.Panel -> {
                NavItem(
                    label = SduiConstants.ComponentType.Panel,
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Default.Home
                )
            }

            SduiConstants.ComponentType.AirportDemoComponent -> {
                NavItem(
                    label = "Air",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Sharp.DateRange
                )
            }

            SduiConstants.ComponentType.HotelDemoComponent -> {
                NavItem(
                    label = "Hotel",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Sharp.List
                )
            }

            SduiConstants.ComponentType.Setting -> {
                NavItem(
                    label = "Setting",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Default.Settings
                )
            }

            SduiConstants.ComponentType.SimpleTopAppBar -> {
                NavItem(
                    label = "TopAppBar",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Default.ExitToApp
                )
            }

            SduiConstants.ComponentType.HomeView -> {
                NavItem(
                    label = "Home",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Default.Home
                )
            }

            SduiConstants.ComponentType.SearchView -> {
                NavItem(
                    label = "Search",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Default.Search
                )
            }

            SduiConstants.ComponentType.PanelSetting -> {
                NavItem(
                    label = "Setting",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Default.Settings
                )
            }

            // Amadeus Api Screens
            SduiConstants.ComponentType.Amadeus.HomeScreen -> {
                NavItem(
                    label = "Home",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Default.Home
                )
            }

            SduiConstants.ComponentType.Amadeus.ScheduleScreen -> {
                NavItem(
                    label = "Schedule",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Default.DateRange
                )
            }

            SduiConstants.ComponentType.Amadeus.Travel -> {
                NavItem(
                    label = "Travel",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Filled.MailOutline
                )
            }

            SduiConstants.ComponentType.Amadeus.Profile -> {
                NavItem(
                    label = "Profile",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Filled.Person
                )
            }

            SduiConstants.ComponentType.Amadeus.Auth.Login -> {
                NavItem(
                    label = "Login",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Filled.Lock
                )
            }

            else -> {
                println("Missing NavItem factory for $childComponentType")
                NavItem(
                    label = "Missing_Factory",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Default.Close
                )
            }
        }
    }

    override fun getComponentInstanceOf(
        componentJson: JsonObject
    ): Component {

        val componentType: String =
            componentJson
                .getValue(SduiConstants.JsonKeyName.componentType)
                .jsonPrimitive
                .content

        return when (componentType) {

            SduiConstants.ComponentType.Panel -> {
                PanelComponent(
                    viewModelFactory = PanelViewModelFactory(
                        sduiHandler = PanelSduiHandler(componentJson, this),
                        panelStatePresenter = PanelComponentDefaults.createPanelStatePresenter(
                            dispatcher = Dispatchers.Main
                        )
                    ),
                    content = PanelComponentDefaults.PanelComponentView
                )
            }

            SduiConstants.ComponentType.Drawer -> {
                DrawerComponent(
                    viewModelFactory = DrawerViewModelFactory(
                        sduiHandler = DrawerSduiHandler(componentJson, this),
                        drawerStatePresenter = DrawerComponentDefaults.createDrawerStatePresenter(
                            dispatcher = Dispatchers.Main
                        )
                    ),
                    content = DrawerComponentDefaults.DrawerComponentView
                )
            }

            SduiConstants.ComponentType.BottomNavigation -> {
                BottomNavigationComponent(
                    viewModelFactory = BottomNavigationViewModelFactory(
                        sduiHandler = BottomNavigationSduiHandler(componentJson, this),
                        bottomNavigationStatePresenter = BottomNavigationComponentDefaults.createBottomNavigationStatePresenter(
                            dispatcher = Dispatchers.Main
                        )
                    ),
                    content = BottomNavigationComponentDefaults.BottomNavigationComponentView
                )
            }

            SduiConstants.ComponentType.Amadeus.Auth.Login -> {

                val accountPlugin: AccountPlugin = get()

                StackComponent<AuthViewModel>(
                    viewModelFactory = AuthViewModelFactory(
                        accountPlugin = accountPlugin,
                        stackStatePresenter = StackComponentDefaults.createStackStatePresenter()
                    ),
                    content = StackComponentDefaults.DefaultStackComponentView
                )
            }

            else -> {
                println("Missing Component factory for $componentType")
                ComponentMissingImplementation(componentType)
            }
        }

    }

}
