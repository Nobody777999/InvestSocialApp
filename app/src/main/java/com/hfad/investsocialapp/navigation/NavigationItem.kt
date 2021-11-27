package com.hfad.investsocialapp.navigation

sealed class NavigationItem(var route: String) {
    object Home : NavigationItem("home")
    object Profile : NavigationItem("profile")
    object Login : NavigationItem("login")
    object CreateRecords : NavigationItem("createRecord")
}