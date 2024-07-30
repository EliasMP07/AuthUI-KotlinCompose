package com.devdroid07.authui_kotlincompose.auth.presentation.login

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

) {

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LoginScreen(
        state = LoginState(),
        snackbarHostState = snackbarHostState,
        onAction = {
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    state: LoginState,
    snackbarHostState: SnackbarHostState,
    onAction: (LoginAction) -> Unit
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
                    onValueChange = {
                        onAction(LoginAction.OnEmailChange(it))
                    },
                    placeholder = stringResource(R.string.input_email),
                    leadingIcon = EmailIcon,
                    contentDescription = stringResource(R.string.email)
                )

                Spacer(modifier = Modifier.height(spacing.spaceMedium))

                AuthUiKotlinComposeTextFieldPassword(
                    value = state.password,
                    onValueChange = {
                        onAction(LoginAction.OnPasswordChange(it))
                    }
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
                        onClick = { offset ->
                            annotationString.getStringAnnotations(
                                tag = "clickable_text",
                                start = offset,
                                end = offset,
                            ).firstOrNull()?.let {

                            }
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