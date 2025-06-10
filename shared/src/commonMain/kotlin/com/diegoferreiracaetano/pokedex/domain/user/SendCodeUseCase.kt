package com.diegoferreiracaetano.pokedex.domain.user

import com.diegoferreiracaetano.pokedex.data.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single

class SendCodeUseCase(
    private val repository: UserRepository,

) : UseCase<String, Flow<String>> {

    override fun invoke(params: String): Flow<String> = flow {
        val user = repository.findByEmail(params).single()
        if (user != null) {
            repository.sendCode(email = user.email)
        }

        emit(params)
    }
}