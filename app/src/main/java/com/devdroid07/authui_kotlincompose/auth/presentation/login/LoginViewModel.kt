package com.devdroid07.authui_kotlincompose.auth.presentation.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> get() = _state.asStateFlow()

    fun onAction(
        action: LoginAction
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
        }
    }
}