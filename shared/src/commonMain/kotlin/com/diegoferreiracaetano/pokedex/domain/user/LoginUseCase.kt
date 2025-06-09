package com.diegoferreiracaetano.pokedex.domain.user

import com.diegoferreiracaetano.pokedex.data.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single

class LoginUseCase(
    private val repository: UserRepository
) : UseCase<Pair<String, String>, Flow<User>> {

    override fun invoke(params: Pair<String, String>): Flow<User> = flow {
        val user = repository.findByUser(params.first, params.second).single()

        if (user != null) {
            emit(user)
        } else {
            throw IllegalArgumentException("Usuário ou senha inválidos.")
        }
    }
}