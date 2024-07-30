package com.devdroid07.authui_kotlincompose.core.presentation.designsystem.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.devdroid07.authui_kotlincompose.R
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.theme.AuthUIKotlinComposeTheme
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.theme.PasswordIcon

/**
 * Campo de texto para contraseñas con varias opciones de personalización.
 *
 * @param modifier Modificador para el campo de texto.
 * @param value Texto actual que se muestra en el campo.
 * @param title Título que se muestra sobre el campo de texto.
 * @param onValueChange Función que se llama cuando el texto cambia.
 * @param contentDescription Descripción para accesibilidad.
 * @param placeholder Texto que se muestra cuando el campo está vacío.
 * @param errorMessage Mensaje de error que se muestra debajo del campo, si hay error.
 * @param isError Indica si hay un error en el campo de texto.
 * @param leadingIcon Icono que se muestra al principio del campo de texto.
 * @param keyboardOptions Opciones del teclado para el campo de texto.
 * @param keyboardActions Acciones del teclado que se ejecutan cuando el usuario interactúa.
 * @param isEnabled Indica si el campo de texto está habilitado.
 */
@Composable
fun AuthUiKotlinComposeTextFieldPassword(
    modifier: Modifier = Modifier,
    value: String,
    title: String = stringResource(id = R.string.password),
    onValueChange: (String) -> Unit,
    contentDescription: String = "",
    placeholder: String = stringResource(id = R.string.example_password),
    errorMessage: String? = null,
    isError: Boolean = false,
    leadingIcon: ImageVector? = PasswordIcon,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),
    isEnabled: Boolean = true,
){
    AuthUiKotlinComposeTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        title = title,
        contentDescription = contentDescription,
        isPassword = true,
        errorMessage = errorMessage,
        isError = isError,
        leadingIcon = leadingIcon,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        isEnabled = isEnabled
    )
}

@Preview
@Composable
private fun AuthUiKotlinComposeTextFieldPasswordPreview(){
    AuthUIKotlinComposeTheme {
        AuthUiKotlinComposeTextFieldPassword(
            value = "",
            onValueChange = {},
            contentDescription = ""
        )
    }
}