package com.devdroid07.authui_kotlincompose.auth.presentation.register

import androidx.lifecycle.ViewModel
import com.devdroid07.authui_kotlincompose.R
import com.devdroid07.authui_kotlincompose.auth.domain.validator.UserDataValidator
import com.devdroid07.authui_kotlincompose.core.presentation.ui.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userDataValidator: UserDataValidator
): ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> get() = _state.asStateFlow()

    fun onAction(action: RegisterAction){
        when(action){
            is RegisterAction.OnEmailChange -> {
                _state.update { registerState -> registerState.copy(email = action.email) }
            }
            RegisterAction.OnLoginClick -> {

            }
            is RegisterAction.OnNumberPhoneChange -> {
                _state.update { registerState -> registerState.copy(numberPhone = action.number) }
            }
            is RegisterAction.OnPasswordChange -> {
                _state.update { registerState -> registerState.copy(password = action.password) }
            }
            RegisterAction.OnRegisterClick -> {
                onRegisterClick()
            }
            is RegisterAction.OnRepeatPasswordChange -> {
                _state.update { registerState -> registerState.copy(repeatPassword = action.password) }
            }
        }
    }

    private fun onRegisterClick() {

        val emailValidationResult = userDataValidator.validateEmail(state.value.email)
        val passwordValidationResult = userDataValidator.validatePassword(state.value.password)
        val phoneValidationResult = userDataValidator.validatePhoneNumber(state.value.numberPhone)
        val passwordsMatchResult = userDataValidator.validatePasswords(state.value.password, state.value.repeatPassword)

        _state.update { registerState ->
            registerState.copy(
                emailError = asignErrorMessage(emailValidationResult),
                passwordError = asignErrorMessage(passwordValidationResult),
                numberPhoneError = asignErrorMessage(phoneValidationResult),
                repeatPasswordError = asignErrorMessage(passwordsMatchResult)
            )
        }
    }

    private fun asignErrorMessage(validationResult: UserDataValidator.ValidationResult): UiText? {
        return when(validationResult) {
            UserDataValidator.ValidationResult.INVALID_EMAIL -> UiText.StringResource(R.string.error_email_invalid)
            UserDataValidator.ValidationResult.WEAK_PASSWORD -> UiText.StringResource(R.string.error_password_weak)
            UserDataValidator.ValidationResult.INVALID_PHONE_NUMBER -> UiText.StringResource(R.string.phone_invalid)
            UserDataValidator.ValidationResult.PASSWORDS_DO_NOT_MATCH -> UiText.StringResource(R.string.error_passwords)
            else -> null
        }
    }

}