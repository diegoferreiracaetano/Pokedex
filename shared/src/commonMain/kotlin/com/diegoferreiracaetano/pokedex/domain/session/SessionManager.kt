package com.diegoferreiracaetano.pokedex.domain.session

import com.diegoferreiracaetano.pokedex.data.session.SessionStorage
import com.diegoferreiracaetano.pokedex.domain.user.User
import com.diegoferreiracaetano.pokedex.util.fromJson
import com.diegoferreiracaetano.pokedex.util.toJson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SessionManager(private val storage: SessionStorage) {
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    suspend fun initialize() {
        _isLoggedIn.value = storage.hasSession()
    }

    suspend fun login(user: User, token: String) {
        storage.saveUser(user.toJson())
        storage.saveToken(token)
        _isLoggedIn.value = true
    }

    suspend fun logout() {
        storage.clear()
        _isLoggedIn.value = false
    }

    fun user(): User?  = storage.getUser()?.fromJson()

    fun token(): String? = storage.getToken()
}
