package com.diegoferreiracaetano.pokedex.domain.user

enum class CreateAccountStepType {
    EMAIL,
    PASSWORD,
    NAME;

    fun next(): CreateAccountStepType? = when (this) {
        EMAIL -> PASSWORD
        PASSWORD -> NAME
        NAME -> null
    }
}