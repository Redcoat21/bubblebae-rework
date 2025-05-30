package com.dahrenericsson.bubblebaerework.presentation.ui.common.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dahrenericsson.bubblebaerework.presentation.Screen
import com.dahrenericsson.bubblebaerework.presentation.theme.Flamingo
import com.dahrenericsson.bubblebaerework.R

@Composable
fun BottomNavigationBar(navHostController: NavHostController) {
    val navBackStackEntry = navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    // Items are index sensitive, so the order matters
    val navigationItems = listOf<Pair<String, Int>>(
        Pair(Screen.Home.Favorite.route, R.drawable.heart_icon),
        Pair(Screen.Livestream.createRoute(null), R.drawable.signal_icon),
        Pair(Screen.Home.Livestreams.route, R.drawable.home_icon),
        Pair(Screen.Home.ChatList.route, R.drawable.home_icon),
        Pair(Screen.Home.Profile.route, R.drawable.home_icon),
    )
    NavigationBar(containerColor = Flamingo, contentColor = Color.White) {
        navigationItems.forEach { (route, iconId) ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(iconId),
                        contentDescription = route
                    )
                },
                selected = true,
                onClick = {
                    if (currentRoute != route) {
                        navHostController.navigate(route) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = false
                            }
                            launchSingleTop = true
                            restoreState = false
                        }
                    }
                }
            )
        }

    }
}