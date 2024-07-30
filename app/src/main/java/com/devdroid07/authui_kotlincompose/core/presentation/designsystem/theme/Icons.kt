package com.devdroid07.authui_kotlincompose.core.presentation.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.devdroid07.authui_kotlincompose.R

val EmailIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.ic_email)

val PasswordIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.ic_password)

val EyeClosedIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.ic_eye_closed)

val EyeOpenedIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.ic_eye_opened)

val PhoneIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.ic_phone)