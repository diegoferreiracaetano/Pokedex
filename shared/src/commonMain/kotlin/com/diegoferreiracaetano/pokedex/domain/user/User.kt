package com.diegoferreiracaetano.pokedex.domain.user

data class User(
    val id: Int = 0,
    val name: String,
    val email: String,
    val password: String
)
