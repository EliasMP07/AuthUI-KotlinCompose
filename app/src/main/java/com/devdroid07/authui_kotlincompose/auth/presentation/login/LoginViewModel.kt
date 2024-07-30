package com.devdroid07.authui_kotlincompose.auth.presentation.login

import androidx.lifecycle.ViewModel
import com.devdroid07.authui_kotlincompose.R
import com.devdroid07.authui_kotlincompose.auth.domain.validator.UserDataValidator
import com.devdroid07.authui_kotlincompose.core.presentation.ui.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * ViewModel para el manejo de la lógica de inicio de sesión.
 *
 * @param userDataValidator Validador de datos de usuario.
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userDataValidator: UserDataValidator,
) : ViewModel() {

    //Variable que contiene los estado de la ui
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> get() = _state.asStateFlow()

    /**
     * Maneja las acciones de la UI y actualiza el estado en consecuencia.
     *
     * @param action Acción disparada desde la UI.
     */
    fun onAction(
        action: LoginAction,
    ) {
        when (action) {
            is LoginAction.OnEmailChange -> {
                _state.update { loginState ->
                    loginState.copy(
                        email = action.email
                    )
                }
            }
            is LoginAction.OnPasswordChange -> {
                _state.update { loginState ->
                    loginState.copy(
                        password = action.password
                    )
                }
            }
            LoginAction.OnToggleCheckBoxClick -> {
                _state.update { loginState ->
                    loginState.copy(
                        isChecked = !state.value.isChecked
                    )
                }
            }
            LoginAction.OnLoginClick -> {
                login()
            }
            else -> Unit
        }
    }

    /**
     * Realiza el proceso de inicio de sesión.
     * Simula un loginSuccess y valida los campos de texto.
     */
    private fun login() {
        val emailValidationResult = userDataValidator.validateEmail(state.value.email)
        val passwordValidationResult = userDataValidator.validatePassword(state.value.password)
        _state.update { loginState ->
            loginState.copy(
                loginSuccess = emailValidationResult == UserDataValidator.ValidationResult.SUCCESS && passwordValidationResult == UserDataValidator.ValidationResult.SUCCESS,
                emailError = asignErrorMessage(emailValidationResult),
                passwordError = asignErrorMessage(passwordValidationResult),
            )
        }
    }

    /**
     * Asigna el mensaje de error basado en el resultado de validación.
     *
     * @param validationResult Resultado de la validación.
     * @return Devuelve el mensaje de error para la UI.
     */
    private fun asignErrorMessage(validationResult: UserDataValidator.ValidationResult): UiText? {
        return when (validationResult) {
            UserDataValidator.ValidationResult.INVALID_EMAIL -> UiText.StringResource(R.string.error_email_invalid)
            UserDataValidator.ValidationResult.WEAK_PASSWORD -> UiText.StringResource(R.string.error_password)
            else -> null
        }
    }

}