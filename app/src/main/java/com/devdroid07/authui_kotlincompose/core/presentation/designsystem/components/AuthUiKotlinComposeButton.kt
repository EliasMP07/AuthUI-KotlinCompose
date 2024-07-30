package com.devdroid07.authui_kotlincompose.core.presentation.designsystem.components

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

/**
 * Botón personalizado para la interfaz de usuario de autenticación en Kotlin Compose.
 *
 * @param text El texto que se muestra dentro del botón.
 * @param modifier Modificador para ajustar el diseño del botón.
 * @param enabled Indica si el botón está habilitado o no.
 * @param onClick Función que se ejecuta cuando se hace clic en el botón.
 */
@Composable
fun AuthUiKotlinComposeButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.background.copy(
                alpha = 0.5f
            ),
            disabledContentColor = MaterialTheme.colorScheme.background.copy(
                alpha = 0.5f
            ),
        ),
        shape = RoundedCornerShape(40f),
        modifier = modifier
            .height(IntrinsicSize.Min)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}
