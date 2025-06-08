package com.diegoferreiracaetano.pokedex.domain.user

import com.diegoferreiracaetano.pokedex.domain.user.CreateAccountStepType.EMAIL
import com.diegoferreiracaetano.pokedex.domain.user.CreateAccountStepType.NAME
import com.diegoferreiracaetano.pokedex.domain.user.CreateAccountStepType.PASSWORD

interface CreateAccountValidator {
    fun isValid(value: String): Boolean
    fun shouldValidate(value: String): Boolean = value.isNotBlank()
    fun getError(value: String): Boolean = !isValid(value) && shouldValidate(value)
}

object EmailValidator : CreateAccountValidator {
    private val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")

    override fun isValid(value: String): Boolean {
        return emailRegex.matches(value)
    }
}

object PasswordValidator : CreateAccountValidator {
    override fun isValid(value: String): Boolean {
        return value.length >= 8
    }
}

object NameValidator : CreateAccountValidator {
    override fun isValid(value: String): Boolean {
        return value.length >= 3
    }
}

fun CreateAccountStepType.getValidationError(value: String) = when(this) {
    NAME -> NameValidator.getError(value)
    EMAIL -> EmailValidator.getError(value)
    PASSWORD -> PasswordValidator.getError(value)
}