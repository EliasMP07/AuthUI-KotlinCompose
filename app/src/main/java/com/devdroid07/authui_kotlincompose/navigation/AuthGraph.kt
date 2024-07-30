package com.devdroid07.authui_kotlincompose.navigation

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.devdroid07.authui_kotlincompose.auth.presentation.login.LoginScreenRoot
import com.devdroid07.authui_kotlincompose.auth.presentation.login.LoginViewModel
import com.devdroid07.authui_kotlincompose.auth.presentation.register.RegisterScreenRoot
import com.devdroid07.authui_kotlincompose.auth.presentation.register.RegisterViewModel
import com.devdroid07.authui_kotlincompose.navigation.utils.RoutesScreens
import com.devdroid07.authui_kotlincompose.navigation.utils.enterTransition
import com.devdroid07.authui_kotlincompose.navigation.utils.exitTransition
import com.devdroid07.authui_kotlincompose.navigation.utils.lifecycleIsResumed
import com.devdroid07.authui_kotlincompose.navigation.utils.navigateToSingleTop
import com.devdroid07.authui_kotlincompose.navigation.utils.popEnterTransition
import com.devdroid07.authui_kotlincompose.navigation.utils.popExitTransition

fun NavGraphBuilder.auth(
    context: Context,
    navController: NavHostController,
) {
    navigation(
        startDestination = RoutesScreens.Login.route,
        route = RoutesScreens.Auth.route
    ) {

        composable(
            route = RoutesScreens.Login.route,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() }
        ) { navBackEntry ->

            val viewModel: LoginViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val onAction = viewModel::onAction

            LoginScreenRoot(
                state = state,
                onAction = onAction,
                context = context,
                onLoginSuccess = {
                    if (navBackEntry.lifecycleIsResumed()) {
                        navController.navigateToSingleTop(RoutesScreens.Register)
                    }
                },
                navigateToRegister = {
                    if (navBackEntry.lifecycleIsResumed()) {
                        navController.navigateToSingleTop(RoutesScreens.Register)
                    }
                }
            )
        }

        composable(
            route = RoutesScreens.Register.route,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() }
        ) { navBackEntry ->

            val viewModel: RegisterViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val onAction = viewModel::onAction

            RegisterScreenRoot(
                state = state,
                context = context,
                onAction = onAction,
                onRegisterSuccess = {
                    if (navBackEntry.lifecycleIsResumed()) {
                        navController.navigateToSingleTop(RoutesScreens.Login)
                    }
                },
                navigateToLogin = {
                    if (navBackEntry.lifecycleIsResumed()) {
                        navController.navigateToSingleTop(RoutesScreens.Login)
                    }
                }
            )
        }

    }
}