package com.macaosoftware.component.navigationcompose.demo.serverui.data

object ServerUiConstants {

    object JsonKeyName {
        const val componentType = "componentType"
        const val children = "children"
    }

    object ComponentType {
        const val BottomNavigation = "BottomNavigation"
        const val Drawer = "Drawer"

        const val SimpleScreen = "SimpleScreen"
        const val SimpleScreen1 = "SimpleScreen1"
        const val Setting = "Setting"

        //Simple TopAppBar
        const val SimpleTopAppBar = "SimpleTopAppBar"

        //Home Screen
        const val HomeView = "HomeView"

        //Search Screen
        const val SearchView = "SearchView"

        //Panel Components
        const val Panel = "Panel"
        const val PanelSetting = "PanelSetting"

        //Amadeus Api Screens
        object Amadeus {
            const val HotelDemoComponent = "HotelDemoComponent"
            const val AirportDemoComponent = "AirportDemoComponent"
            const val HomeScreen = "HomeScreen"
            const val ScheduleScreen = "ScheduleScreen"
            const val Travel = "TravelScreen"
            const val Profile = "Profile"

            object Auth {
                const val Login = "Login"
                const val Signup = "Signup"
                const val Forget = "Forget"
            }
        }
    }
}
