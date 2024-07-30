package com.devdroid07.authui_kotlincompose.auth.presentation.register

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val numberPhone: String = ""
)
