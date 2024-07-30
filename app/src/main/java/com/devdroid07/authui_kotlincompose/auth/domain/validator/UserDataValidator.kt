package com.devdroid07.authui_kotlincompose.auth.domain.validator

class UserDataValidator {

    fun validateEmail(email: String): ValidationResult {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return if (email.matches(emailRegex)) {
            ValidationResult.SUCCESS
        } else {
            ValidationResult.INVALID_EMAIL
        }
    }

    fun validatePassword(password: String): ValidationResult {
        val passwordRegex = Regex("^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}")
        return if (password.matches(passwordRegex)) {
            ValidationResult.SUCCESS
        } else {
            ValidationResult.WEAK_PASSWORD
        }
    }

    fun validatePhoneNumber(number: String): ValidationResult {
        val phoneRegex = Regex("^\\+?[1-9]\\d{1,14}\$")
        return if (number.matches(phoneRegex)) {
            ValidationResult.SUCCESS
        } else {
            ValidationResult.INVALID_PHONE_NUMBER
        }
    }

    fun validatePasswords(password: String, confirmPassword: String): ValidationResult {
        if (password != confirmPassword) {
            return ValidationResult.PASSWORDS_DO_NOT_MATCH
        }
        return ValidationResult.SUCCESS
    }

    enum class ValidationResult {
        SUCCESS,
        INVALID_EMAIL,
        WEAK_PASSWORD,
        INVALID_PHONE_NUMBER,
        PASSWORDS_DO_NOT_MATCH
    }
}
