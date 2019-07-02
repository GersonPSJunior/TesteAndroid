package br.com.resourceit.nasa.bank_santander.utils

import android.util.Patterns

object Validation {
    fun emailValidation(email: String): Boolean = email.matches(Patterns.EMAIL_ADDRESS.toRegex())

    fun requiredField(field: String): Boolean = field.trim().isEmpty() || field.trim().isBlank()


}