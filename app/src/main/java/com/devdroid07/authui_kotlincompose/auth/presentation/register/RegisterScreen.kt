package com.devdroid07.authui_kotlincompose.auth.presentation.register

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devdroid07.authui_kotlincompose.R
import com.devdroid07.authui_kotlincompose.auth.presentation.login.LoginAction
import com.devdroid07.authui_kotlincompose.auth.presentation.login.LoginState
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.components.AuthUiKotlinComposeButton
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.components.AuthUiKotlinComposeTextField
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.components.AuthUiKotlinComposeTextFieldPassword
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.components.ClickableText
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.components.ImageBackground
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.theme.AuthUIKotlinComposeTheme
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.theme.EmailIcon
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.theme.LocalSpacing
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.theme.PhoneIcon

@Composable
fun RegisterScreenRoot(

) {

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    RegisterScreen(
        state = RegisterState(),
        snackbarHostState = snackbarHostState,
        onAction = {
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(
    state: RegisterState,
    snackbarHostState: SnackbarHostState,
    onAction: (RegisterAction) -> Unit
) {
    val spacing = LocalSpacing.current
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
                    text = stringResource(R.string.create_account),
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
                    onValueChange = {
                        onAction(RegisterAction.OnEmailChange(it))
                    },
                    placeholder = stringResource(R.string.input_email),
                    leadingIcon = EmailIcon,
                    contentDescription = stringResource(R.string.email)
                )

                Spacer(modifier = Modifier.height(spacing.spaceMedium))

                AuthUiKotlinComposeTextField(
                    value = state.numberPhone,
                    title = stringResource(R.string.phone),
                    onValueChange = {
                        onAction(RegisterAction.OnNumberPhoneChange(it))
                    },
                    placeholder = stringResource(R.string.example_phone),
                    leadingIcon = PhoneIcon,
                    contentDescription = stringResource(R.string.email)
                )

                Spacer(modifier = Modifier.height(spacing.spaceMedium))

                AuthUiKotlinComposeTextFieldPassword(
                    value = state.password,
                    onValueChange = {
                        onAction(RegisterAction.OnPasswordChange(it))
                    }
                )

                Spacer(modifier = Modifier.height(spacing.spaceMedium))

                AuthUiKotlinComposeTextFieldPassword(
                    value = state.repeatPassword,
                    placeholder = stringResource(id = R.string.repeat_password),
                    title = stringResource(id = R.string.confim_password),
                    onValueChange = {
                        onAction(RegisterAction.OnRepeatPasswordChange(it))
                    }
                )

                Spacer(modifier = Modifier.height(spacing.spaceSmall))

                AuthUiKotlinComposeButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = spacing.spaceMedium,
                            vertical = spacing.spaceMedium
                        ),
                    text = stringResource(R.string.register_btn_text),
                    onClick = {}
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
                        append(stringResource(id = R.string.have_an_account))
                        append(" ")
                    }
                    pushStringAnnotation(
                        tag = "clickable_text",
                        annotation = stringResource(id = R.string.have_an_account)
                    )
                    withStyle(
                        style = SpanStyle(
                            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        append(stringResource(id = R.string.login_btn_text))
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

                        }
                    }
                )

            }
        }
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    AuthUIKotlinComposeTheme {
        RegisterScreen(
            state = RegisterState(),
            snackbarHostState = SnackbarHostState(),
            onAction = {})
    }
}