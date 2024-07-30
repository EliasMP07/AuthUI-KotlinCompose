package com.devdroid07.authui_kotlincompose.auth.presentation.login

import androidx.compose.runtime.Stable
import com.devdroid07.authui_kotlincompose.core.presentation.ui.UiText

@Stable
data class LoginState(
    val email: String = "",
    val emailError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
    val isChecked: Boolean = false,
    val loginSuccess: Boolean = false
)
