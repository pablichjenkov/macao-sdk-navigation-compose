package com.macaosoftware.component.drawer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.macaosoftware.component.util.BackPressHandler
import com.macaosoftware.component.util.EmptyNavigationView
import com.macaosoftware.plugin.lifecycle.LifecycleEventObserver

@Composable
fun DrawerView(
    viewModel: DrawerViewModel,
    modifier: Modifier = Modifier
) {

    BackPressHandler {
        viewModel.handleBackPressed()
    }

    LifecycleEventObserver(
        lifecycleOwner = LocalLifecycleOwner.current,
        onStart = {
            println("Receiving DrawerView.onStart() event")
            viewModel.onStart()
        },
        onStop = {
            println("Receiving DrawerView.onStop() event")
            viewModel.onStop()
        },
        initializeBlock = {
            viewModel.onAttach()
        }
    )

    val navController = rememberNavController()

    val navItems = viewModel.drawerStatePresenter.navItemsState.value

    if (navItems.isNotEmpty()) {
        NavigationDrawer(
            modifier = modifier,
            statePresenter = viewModel.drawerStatePresenter,
            navController = navController,
            navItems = navItems
        )
    } else {
        EmptyNavigationView()
    }
}

@Composable
fun NavigationDrawer(
    statePresenter: DrawerStatePresenter,
    navController: NavHostController,
    navItems: List<DrawerNavItem>,
    modifier: Modifier = Modifier
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerContent = {
            DrawerContent(modifier, statePresenter)
        },
        modifier = modifier,
        drawerState = drawerState,
        gesturesEnabled = true,
        scrimColor = DrawerDefaults.scrimColor
    ) {
        NavHost(
            navController = navController,
            route = "Component-Drawer",
            startDestination = navItems[0].label,
            modifier = modifier.fillMaxSize()
            //.verticalScroll(rememberScrollState())
            //.padding(innerPadding)
        ) {
            navItems.forEach { drawerNavItem ->
                composable(drawerNavItem.label) { backstackEntry ->
                    drawerNavItem.composableStateMapper.ContentForRoute(
                        navController,
                        backstackEntry
                    )
                }
            }
        }
    }

    LaunchedEffect(key1 = statePresenter) {
        statePresenter.drawerOpenFlow.collect { drawerValue ->
            when (drawerValue) {
                DrawerValue.Closed -> drawerState.close()
                DrawerValue.Open -> drawerState.open()
            }
        }
    }

    LaunchedEffect(key1 = statePresenter) {
        statePresenter.navItemClickFlow.collect { navItem ->
            navController.navigate(navItem.label)
        }
    }

}

@Composable
private fun DrawerContent(
    modifier: Modifier = Modifier,
    statePresenter: DrawerStatePresenter
) {
    val navItems by statePresenter.navItemsState
    val drawerHeaderState by statePresenter.drawerHeaderState
    val drawerStyle = statePresenter.drawerStyle

    ModalDrawerSheet {
        Column(
            modifier = modifier.fillMaxSize(),
        ) {
            DrawerHeader(drawerHeaderState = drawerHeaderState)
            DrawerContentList(
                navItems = navItems,
                drawerStyle = drawerStyle,
                onNavItemClick = { navItem -> statePresenter.navItemClick(navItem) }
            )
        }
    }
}

@Composable
private fun DrawerContentList(
    modifier: Modifier = Modifier,
    navItems: List<DrawerNavItem>,
    drawerStyle: DrawerStyle,
    onNavItemClick: (DrawerNavItem) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize().padding(8.dp).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalAlignment = drawerStyle.alignment
    ) {
        for (navItem in navItems) {
            NavigationDrawerItem(
                label = {
                    Text(
                        text = navItem.label,
                        color = drawerStyle.itemTextColor,
                        fontSize = drawerStyle.itemTextSize
                    )
                },
                icon = { Icon(navItem.icon, null) },
                selected = navItem.selected,
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = drawerStyle.selectedColor,
                    unselectedContainerColor = drawerStyle.unselectedColor,
                ),
                onClick = { onNavItemClick(navItem) }
            )
        }
    }
}

val LocalDrawerNavigationProvider =
    staticCompositionLocalOf<DrawerNavigationProvider> {
        EmptyDrawerNavigationProvider()
    }
