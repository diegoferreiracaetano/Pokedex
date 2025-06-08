package com.diegoferreiracaetano.pokedex.domain.user

import com.diegoferreiracaetano.pokedex.data.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update

class CreateAccountUseCase(
    private val repository: UserRepository
) : UseCase<Pair<CreateAccountStepType, String>, Flow<Boolean>> {

    private val _savedData = MutableStateFlow<Map<CreateAccountStepType, String>>(emptyMap())

    override fun invoke(params: Pair<CreateAccountStepType, String>) = flow {
        _savedData.update { it + params }

       val result = if (isFinished()) {
            repository.save(user())
            true
        } else {
            false
        }

        emit(result)
    }

    private fun isFinished() = _savedData.value.size == CreateAccountStepType.entries.size

    private fun user() = User(
        name = _savedData.value[CreateAccountStepType.NAME]!!,
        email = _savedData.value[CreateAccountStepType.EMAIL]!!,
        password = _savedData.value[CreateAccountStepType.PASSWORD]!!
    )
}