package com.devdroid07.authui_kotlincompose.navigation.utils

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

//Funcion de extension para navegar a otra pantalla y limpiar las anteriores
fun NavHostController.navigateToSingleTop(route: RoutesScreens) {
    navigate(route.route) {
        popUpTo(graph.id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}

//Funcion que validad si la pantalla ha cargado correctamente para poder pasar a la siguiente.
fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED