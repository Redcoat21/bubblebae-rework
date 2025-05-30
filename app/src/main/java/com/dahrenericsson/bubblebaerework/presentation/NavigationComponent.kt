package com.dahrenericsson.bubblebaerework.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.dahrenericsson.bubblebaerework.presentation.ui.auth.screen.LoginScreen
import com.dahrenericsson.bubblebaerework.presentation.ui.auth.screen.RegisterScreen
import com.dahrenericsson.bubblebaerework.presentation.ui.auth.screen.WelcomeScreen
import com.dahrenericsson.bubblebaerework.presentation.ui.common.component.BottomNavigationBar
import com.dahrenericsson.bubblebaerework.presentation.ui.home.screen.HomeScreen

@Composable
fun NavigationComponent() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar(currentRoute)) {
                BottomNavigationBar(navHostController = navController)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Auth.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            navigation(startDestination = Screen.Auth.Welcome.route, route = Screen.Auth.route) {
                composable(route = Screen.Auth.Welcome.route) {
                    WelcomeScreen(navHostController = navController)
                }

                composable(route = Screen.Auth.Login.route) {
                    LoginScreen(navHostController = navController)
                }

                composable(route = Screen.Auth.Register.route) {
                    RegisterScreen(navHostController = navController)
                }
            }

            navigation(
                startDestination = Screen.Home.Livestreams.route,
                route = Screen.Home.route
            ) {
                composable(route = Screen.Home.Livestreams.route) {
                    HomeScreen()
                }
            }
        }
    }

}

private fun shouldShowBottomBar(route: String?): Boolean {
    return route in listOf(
        Screen.Home.Livestreams.route, Screen.Home.ChatList.route, Screen.Livestream.route,
        Screen.Home.Favorite.route
    ) // screens with bottom bar
}