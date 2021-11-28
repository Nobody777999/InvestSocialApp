package com.hfad.investsocialapp.navigation

sealed class NavigationItem(var route: String) {
    // объектры основных пакетов приложения
    object Home : NavigationItem("home")
    object Profile : NavigationItem("profile")
    object Login : NavigationItem("login")
    object CreateRecord : NavigationItem("createRecord")
    object Comments: NavigationItem("comments")
}