package com.diegoferreiracaetano.pokedex.di

import com.diegoferreiracaetano.pokedex.data.user.UserRepository
import com.diegoferreiracaetano.pokedex.data.user.source.remote.TaskNetworkDataSource
import com.diegoferreiracaetano.pokedex.data.user.source.remote.UserRepositoryRemote
import com.diegoferreiracaetano.pokedex.domain.user.CreateAccountUseCase
import com.diegoferreiracaetano.pokedex.domain.user.LoginUseCase
import org.koin.dsl.module

val sharedModule = module {

    single { TaskNetworkDataSource() }

    single<UserRepository> { UserRepositoryRemote(get()) }

    single { CreateAccountUseCase(get()) }
    single { LoginUseCase(get()) }
}