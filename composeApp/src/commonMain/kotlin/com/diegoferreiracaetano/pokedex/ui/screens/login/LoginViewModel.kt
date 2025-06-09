package com.diegoferreiracaetano.pokedex.ui.screens.login

import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.pokedex.domain.user.LoginUseCase
import com.diegoferreiracaetano.pokedex.ui.util.produceUiState
import kotlinx.coroutines.flow.MutableStateFlow

class LoginViewModel(
    private val loginUseCase: LoginUseCase
): ViewModel() {

    private val _trigger = MutableStateFlow<Pair<String, String>?>(null)

    val uiState = produceUiState(_trigger) {
        loginUseCase(it)
    }

    fun login(email: String, password: String) {
        _trigger.value = email to password
    }
}