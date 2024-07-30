package com.devdroid07.authui_kotlincompose.auth.presentation.login

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devdroid07.authui_kotlincompose.R
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.components.AuthUiKotlinComposeButton
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.components.AuthUiKotlinComposeTextField
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.components.AuthUiKotlinComposeTextFieldPassword
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.components.ClickableText
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.components.ImageBackground
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.theme.AuthUIKotlinComposeTheme
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.theme.EmailIcon
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.theme.LocalSpacing
import kotlinx.coroutines.launch

@Composable
fun LoginScreenRoot(
    state: LoginState,
    context: Context,
    onAction: (LoginAction) -> Unit,
    onLoginSuccess: () -> Unit,
    navigateToRegister: () -> Unit,
) {
    //Scope para poder mostrar el snackbar
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    //Launch effect para cambiar a la otra pantalla cuando se inicia sesion
    LaunchedEffect(key1 = state.loginSuccess) {
        if (state.loginSuccess) {
            onLoginSuccess()
        }
    }


    LoginScreen(
        state = state,
        snackbarHostState = snackbarHostState,
        onAction = { action ->
            onAction(action)
            when (action) {
                LoginAction.OnToggleCheckBoxClick -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(if (state.isChecked) context.getString(R.string.disable_checkbox) else context.getString(R.string.active_checkbox))
                    }
                }
                LoginAction.OnForgotPasswordClick -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(context.getString(R.string.reset_password))
                    }
                }
                LoginAction.OnLoginClick -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(if (state.isChecked) context.getString(R.string.active_checkbox) else context.getString(R.string.btn_login_press))
                    }
                }
                LoginAction.OnRegisterClick -> navigateToRegister()
                else -> Unit
            }
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    state: LoginState,
    snackbarHostState: SnackbarHostState,
    onAction: (LoginAction) -> Unit,
) {

    val spacing = LocalSpacing.current
    val focusManager = LocalFocusManager.current

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        ImageBackground {
            Column(
                modifier = Modifier.padding(spacing.spaceMedium)
            ) {

                Text(
                    text = stringResource(R.string.login),
                    style = MaterialTheme.typography.headlineMedium,
                )

                HorizontalDivider(
                    modifier = Modifier.width(spacing.spaceExtraLarge),
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(spacing.spaceLarge))

                AuthUiKotlinComposeTextField(
                    value = state.email,
                    title = stringResource(R.string.email),
                    errorMessage = state.emailError?.asString(),
                    isError = state.emailError != null,
                    onValueChange = {
                        onAction(LoginAction.OnEmailChange(it))
                    },
                    placeholder = stringResource(R.string.input_email),
                    leadingIcon = EmailIcon,
                    contentDescription = stringResource(R.string.email),
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onAny = {
                            focusManager.moveFocus(FocusDirection.Next)
                        }
                    )
                )

                Spacer(modifier = Modifier.height(spacing.spaceMedium))

                AuthUiKotlinComposeTextFieldPassword(
                    value = state.password,
                    errorMessage = state.passwordError?.asString(),
                    isError = state.passwordError != null,
                    onValueChange = {
                        onAction(LoginAction.OnPasswordChange(it))
                    },
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onAny = {
                            focusManager.clearFocus()
                        }
                    )
                )

                Spacer(modifier = Modifier.height(spacing.spaceSmall))

                val annotationString = buildAnnotatedString {
                    pushStringAnnotation(
                        tag = "clickable_text",
                        annotation = stringResource(id = R.string.forgotPassword)
                    )
                    withStyle(
                        style = SpanStyle(
                            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        append(stringResource(id = R.string.forgotPassword))
                    }
                    pop()
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = state.isChecked,
                        onCheckedChange = {
                            onAction(LoginAction.OnToggleCheckBoxClick)
                        }
                    )
                    Text(
                        text = stringResource(R.string.save_remember_me_option),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    ClickableText(
                        modifier = Modifier
                            .padding(vertical = spacing.spaceSmall),
                        text = annotationString,
                        onClick = { _ ->
                            onAction(LoginAction.OnForgotPasswordClick)
                        })
                }

                AuthUiKotlinComposeButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = spacing.spaceMedium,
                            vertical = spacing.spaceMedium
                        ),
                    text = stringResource(R.string.login_btn_text),
                    onClick = {
                        onAction(LoginAction.OnLoginClick)
                    }
                )

                val annotatedString = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                            fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    ) {
                        append(stringResource(id = R.string.dont_have_an_account))
                        append(" ")
                    }
                    pushStringAnnotation(
                        tag = "clickable_text",
                        annotation = stringResource(id = R.string.register)
                    )
                    withStyle(
                        style = SpanStyle(
                            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        append(stringResource(id = R.string.register))
                    }
                    pop()
                }

                ClickableText(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(
                            end = spacing.spaceMedium,
                            top = spacing.spaceSmall
                        ),
                    text = annotatedString,
                    onClick = { offset ->
                        annotatedString.getStringAnnotations(
                            tag = "clickable_text",
                            start = offset,
                            end = offset,
                        ).firstOrNull()?.let {
                            onAction(LoginAction.OnRegisterClick)
                        }
                    }
                )

            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    AuthUIKotlinComposeTheme {
        LoginScreen(
            state = LoginState(),
            snackbarHostState = SnackbarHostState(),
            onAction = {})
    }
}