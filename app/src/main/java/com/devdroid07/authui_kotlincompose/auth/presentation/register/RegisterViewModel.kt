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

/**
 * ViewModel para el manejo de la lógica de registro
 *
 * @param userDataValidator Validador de datos de usuario.
 */
@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userDataValidator: UserDataValidator,
) : ViewModel() {

    //Variable que contiene los estado de la ui
    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> get() = _state.asStateFlow()

    /**
     * Maneja las acciones de la UI y actualiza el estado en consecuencia.
     *
     * @param action Acción disparada desde la UI.
     */
    fun onAction(action: RegisterAction) {
        when (action) {
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

    /**
     * Funcion que seria el registro pero verifica los campos de texto y si estabien, haria quel registro fuero exitoso
     */
    private fun onRegisterClick() {
        // Valida los datos del usuario
        val validationResults = validateUserData()
        // Comprueba si todos los datos son válidos
        val allValid = validationResults.all { it.first == UserDataValidator.ValidationResult.SUCCESS }

        // Actualiza el estado con los resultados de validación pero si hubiera un backen pues iria al registro
        _state.update { registerState ->
            validationResults.fold(registerState) { acc, (result, updateFunc) ->
                updateFunc(
                    acc,
                    asignErrorMessage(result)
                )
            }.copy(registerSuccess = allValid)
        }
    }

    /**
     * Valida los datos del usuario.
     *
     * @return Lista de resultados de validación con sus respectivas funciones de actualización de estado.
     */
    private fun validateUserData(): List<Pair<UserDataValidator.ValidationResult, (RegisterState, UiText?) -> RegisterState>> {
        return listOf(
            userDataValidator.validateEmail(state.value.email) to { state, error -> state.copy(emailError = error) },
            userDataValidator.validatePassword(state.value.password) to { state, error -> state.copy(passwordError = error) },
            userDataValidator.validatePhoneNumber(state.value.numberPhone) to { state, error -> state.copy(numberPhoneError = error) },
            userDataValidator.validatePasswords(
                state.value.password,
                state.value.repeatPassword
            ) to { state, error -> state.copy(repeatPasswordError = error) }
        )
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
            UserDataValidator.ValidationResult.WEAK_PASSWORD -> UiText.StringResource(R.string.error_password_weak)
            UserDataValidator.ValidationResult.INVALID_PHONE_NUMBER -> UiText.StringResource(R.string.phone_invalid)
            UserDataValidator.ValidationResult.PASSWORDS_DO_NOT_MATCH -> UiText.StringResource(R.string.error_passwords)
            else -> null
        }
    }

}