package com.diegoferreiracaetano.pokedex.data.user.source.remote

import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class TaskNetworkDataSource {

    private val accessMutex = Mutex()
    private var users = listOf(
        UserRemote(
            id = 1,
            name = "Diego",
            email = "diego@gmail.com",
            password = "teste"
        ),
        UserRemote(
            id = 2,
            name = "Lucas",
            email = "lucas@gmail.com",
            password = "teste"
        ),
    )

    suspend fun loadUser(): List<UserRemote> = accessMutex.withLock {
        delay(SERVICE_LATENCY_IN_MILLIS)
        return users
    }

    suspend fun saveUser(newUser: UserRemote) = accessMutex.withLock {
        delay(SERVICE_LATENCY_IN_MILLIS)
        users + newUser

        Unit
    }
}

private const val SERVICE_LATENCY_IN_MILLIS = 2000L