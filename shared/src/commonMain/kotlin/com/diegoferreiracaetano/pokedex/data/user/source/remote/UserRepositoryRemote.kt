package com.diegoferreiracaetano.pokedex.data.user.source.remote

import com.diegoferreiracaetano.pokedex.data.user.UserRepository
import com.diegoferreiracaetano.pokedex.domain.user.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class UserRepositoryRemote(
    private val dataSource: TaskNetworkDataSource
): UserRepository {

    override fun users() = flow {
        emit(dataSource.loadUser().toDomainList())
    }

    override suspend fun save(user: User) {
        dataSource.saveUser(user.toExternal())
    }

    override fun findByUser(
        email: String,
        password: String
    ) = flow {

        val user = dataSource.findByUser(
            email,
            password
        )?.toDomain()

        emit(user)
    }
}