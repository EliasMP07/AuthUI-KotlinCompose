package com.devdroid07.authui_kotlincompose.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.devdroid07.authui_kotlincompose.navigation.utils.RoutesScreens

@Composable
fun NavigationRoot(
    navController: NavHostController,
    context: Context,
) {
    NavHost(
        navController = navController,
        startDestination = RoutesScreens.Auth.route
    ) {
        auth(
            context,
            navController
        )
    }
}