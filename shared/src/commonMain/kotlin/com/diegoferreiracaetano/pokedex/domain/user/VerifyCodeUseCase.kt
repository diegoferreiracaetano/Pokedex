package com.diegoferreiracaetano.pokedex.domain.user

import com.diegoferreiracaetano.pokedex.data.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VerifyCodeUseCase(
    private val repository: UserRepository,
) : UseCase<Pair<String, String>, Flow<Boolean>> {

    override fun invoke(params: Pair<String, String>): Flow<Boolean> = flow {
        val result = repository.getCode() == params.first + params.second
        if (!result) {
            throw IllegalArgumentException("O Código é inválido, tente novamente!")
        }
        emit(result)
    }
}