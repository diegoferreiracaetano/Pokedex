package com.diegoferreiracaetano.pokedex.ui.screens.forgotPassword

import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.pokedex.domain.user.SendCodeUseCase
import com.diegoferreiracaetano.pokedex.ui.util.produceUiState
import kotlinx.coroutines.flow.MutableStateFlow

class ValidateEmailViewModel(
    private val sendCodeUseCase: SendCodeUseCase
): ViewModel() {

    private val _trigger = MutableStateFlow<String?>(null)

    val uiState = produceUiState(_trigger) {
        sendCodeUseCase(it)
    }

    fun recoveryPassword(email: String) {
        _trigger.value = email
    }
}