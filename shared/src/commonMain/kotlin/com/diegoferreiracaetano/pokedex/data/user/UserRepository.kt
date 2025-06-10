package com.diegoferreiracaetano.pokedex.data.user

import com.diegoferreiracaetano.pokedex.domain.user.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun users(): Flow<List<User>>
    suspend fun save(user: User)
    fun findByUser(email: String, password: String): Flow<User?>
    fun findByEmail(email: String): Flow<User?>
    suspend fun sendCode(email: String)
    suspend fun getCode(): String?
}