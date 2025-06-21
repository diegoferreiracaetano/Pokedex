package com.diegoferreiracaetano.pokedex.data.session

interface SessionStorage {
    suspend fun saveToken(token: String)
    fun getToken(): String?
    fun hasSession(): Boolean

    suspend fun saveUser(userJson: String)
    fun getUser(): String?

    suspend fun clear()
}