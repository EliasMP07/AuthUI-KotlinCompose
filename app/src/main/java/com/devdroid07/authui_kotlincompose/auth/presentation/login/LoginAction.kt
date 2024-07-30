package com.devdroid07.authui_kotlincompose.auth.presentation.login

sealed interface LoginAction {
    data class OnEmailChange(val email: String): LoginAction
    data class OnPasswordChange(val password: String): LoginAction
    data object OnToggleCheckBoxClick: LoginAction
    data object OnForgotPasswordClick: LoginAction
    data object OnRegisterClick: LoginAction
    data object OnLoginClick: LoginAction
}