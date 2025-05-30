package com.dahrenericsson.bubblebaerework.presentation

sealed class Screen(val route: String) {
    object Auth : Screen("auth") {
        object Welcome : Screen("auth/welcome")
        object Login : Screen("auth/login")
        object Register : Screen("auth/register")
    }

    object Home : Screen("home") {
        object Favorite : Screen("home/favorite")
        object Livestreams : Screen("home/livestreams")
        object ChatList : Screen("home/chatlist")
        object Profile : Screen("home/profile")
    }

    object Livestream : Screen("livestream/{id}") {
        fun createRoute(id: String?): String {
            return "livestream/${id ?: "null"}"
        }
    }

    object Camera : Screen("camera")
    object Chat : Screen("chat/{id}") {
        fun createRoute(id: String): String {
            return "chat/$id"
        }
    }
}