package com.devdroid07.authui_kotlincompose.auth.presentation.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isChecked: Boolean = false,
)
