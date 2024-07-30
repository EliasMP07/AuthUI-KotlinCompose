package com.devdroid07.authui_kotlincompose.navigation.utils

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.navigation.NavBackStackEntry

fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition() =
    slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(500)
    )

fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition() =
    slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(500)
    )

fun AnimatedContentTransitionScope<NavBackStackEntry>.popExitTransition() =
    slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(500)
    )

fun AnimatedContentTransitionScope<NavBackStackEntry>.popEnterTransition() =
    slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(500)
    )