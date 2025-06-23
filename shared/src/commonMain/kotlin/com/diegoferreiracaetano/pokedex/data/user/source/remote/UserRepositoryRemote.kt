package com.diegoferreiracaetano.pokedex.data.user.source.remote

import com.diegoferreiracaetano.pokedex.data.user.UserRepository
import com.diegoferreiracaetano.pokedex.domain.user.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryRemote(
    private val dataSource: UserNetworkDataSource
): UserRepository {

    private var code: String? = null

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

    override fun findByEmail(email: String): Flow<User?> =  flow {
        emit(dataSource.findByEmail(email)?.toDomain())
    }

    override suspend fun sendCode(email: String) {
        delay(2000)
        code = email + "123456"
    }

    override suspend fun getCode(): String? {
        delay(2000)
        return code
    }
}