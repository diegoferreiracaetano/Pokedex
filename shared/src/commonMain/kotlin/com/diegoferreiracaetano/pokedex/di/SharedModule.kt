package com.diegoferreiracaetano.pokedex.di

import com.diegoferreiracaetano.pokedex.data.session.SessionStorage
import com.diegoferreiracaetano.pokedex.data.session.SettingsSessionStorage
import com.diegoferreiracaetano.pokedex.data.user.UserRepository
import com.diegoferreiracaetano.pokedex.data.user.source.remote.TaskNetworkDataSource
import com.diegoferreiracaetano.pokedex.data.user.source.remote.UserRepositoryRemote
import com.diegoferreiracaetano.pokedex.domain.session.SessionManager
import com.diegoferreiracaetano.pokedex.domain.user.CreateAccountUseCase
import com.diegoferreiracaetano.pokedex.domain.user.LoginUseCase
import com.diegoferreiracaetano.pokedex.domain.user.SendCodeUseCase
import com.diegoferreiracaetano.pokedex.domain.user.VerifyCodeUseCase
import com.russhwolf.settings.Settings
import org.koin.dsl.module

val sharedModule = module {

    single { TaskNetworkDataSource() }
    single<UserRepository> { UserRepositoryRemote(get()) }
    single { CreateAccountUseCase(get()) }
    single { LoginUseCase(get(), get()) }
    single { SendCodeUseCase(get()) }
    single { VerifyCodeUseCase(get()) }
    single { Settings() }
    single<SessionStorage> { SettingsSessionStorage(get()) }
    single { SessionManager(get()) }
}