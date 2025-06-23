package com.diegoferreiracaetano.pokedex.di

import com.diegoferreiracaetano.pokedex.ui.screens.account.CreateAccountViewModel
import com.diegoferreiracaetano.pokedex.ui.screens.email.ValidateEmailViewModel
import com.diegoferreiracaetano.pokedex.ui.screens.home.HomeViewModel
import com.diegoferreiracaetano.pokedex.ui.screens.login.LoginViewModel
import com.diegoferreiracaetano.pokedex.ui.screens.otp.SendCodeViewModel
import com.diegoferreiracaetano.pokedex.ui.screens.password.ChangePasswordViewModel
import com.diegoferreiracaetano.pokedex.ui.screens.user.ChangeUserNameViewModel
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
    factory { ValidateEmailViewModel(get()) }
    factory { SendCodeViewModel(get()) }
    factory { ChangeUserNameViewModel() }
    factory { ChangePasswordViewModel() }
    factory { HomeViewModel(get()) }
}