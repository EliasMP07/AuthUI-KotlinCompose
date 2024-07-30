package com.devdroid07.authui_kotlincompose.auth.presentation.register

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(): ViewModel() {

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

            }
            is RegisterAction.OnRepeatPasswordChange -> {
                _state.update { registerState -> registerState.copy(repeatPassword = action.password) }
            }
        }
    }
}