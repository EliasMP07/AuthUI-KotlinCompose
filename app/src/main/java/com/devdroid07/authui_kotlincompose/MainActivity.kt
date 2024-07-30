package com.devdroid07.authui_kotlincompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.devdroid07.authui_kotlincompose.auth.presentation.login.LoginScreenRoot
import com.devdroid07.authui_kotlincompose.auth.presentation.register.RegisterScreenRoot
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.theme.AuthUIKotlinComposeTheme
import com.devdroid07.authui_kotlincompose.navigation.NavigationRoot
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AuthUIKotlinComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    val  navController = rememberNavController()
                    NavigationRoot(
                        navController = navController,
                        context = this
                    )
                }
            }
        }
    }
}