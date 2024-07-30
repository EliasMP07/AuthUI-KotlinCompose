package com.devdroid07.authui_kotlincompose.auth.presentation.register

import androidx.compose.runtime.Stable
import com.devdroid07.authui_kotlincompose.core.presentation.ui.UiText

@Stable
data class RegisterState(
    val email: String = "",
    val emailError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
    val numberPhone: String = "",
    val numberPhoneError: UiText? = null,
    val repeatPassword: String = "",
    val repeatPasswordError: UiText? = null,
    val registerSuccess: Boolean = false
)
