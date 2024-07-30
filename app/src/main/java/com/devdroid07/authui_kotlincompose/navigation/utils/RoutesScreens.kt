package com.devdroid07.authui_kotlincompose.navigation.utils

sealed class RoutesScreens(val route: String){

    data object Auth: RoutesScreens("auth")

    data object Login: RoutesScreens("login")

    data object Register: RoutesScreens("register")

}