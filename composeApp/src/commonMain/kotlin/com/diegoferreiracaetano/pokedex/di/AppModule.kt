package com.diegoferreiracaetano.pokedex.di

import com.diegoferreiracaetano.pokedex.ui.screens.account.CreateAccountViewModel
import com.diegoferreiracaetano.pokedex.ui.screens.login.LoginViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() {
    startKoin {
        modules(appModule + sharedModule)
    }
}

val appModule = module {
    factory { CreateAccountViewModel(get()) }
    factory { LoginViewModel(get()) }
}