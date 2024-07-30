package com.devdroid07.authui_kotlincompose.auth.presentation.register


sealed interface RegisterAction {
    data class OnEmailChange(val email: String): RegisterAction
    data class OnPasswordChange(val password: String): RegisterAction
    data class OnRepeatPasswordChange(val password: String): RegisterAction
    data class OnNumberPhoneChange(val number: String): RegisterAction
    data object OnLoginClick: RegisterAction
    data object OnRegisterClick: RegisterAction
}