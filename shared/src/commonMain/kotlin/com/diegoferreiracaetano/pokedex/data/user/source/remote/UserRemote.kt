package com.diegoferreiracaetano.pokedex.data.user.source.remote

import com.diegoferreiracaetano.pokedex.domain.user.User

class UserRemote(
    val id: Int,
    val name: String,
    val email: String,
    val password: String
)


fun List<UserRemote>.toDomainList() = map(UserRemote::toDomain)

fun UserRemote.toDomain() = User(
    id = id,
    name = name,
    email = email,
    password = password
)

fun List<User>.toExternal() = map(User::toExternal)

fun User.toExternal() = UserRemote(
    id = id,
    name = name,
    email = email,
    password = password
)