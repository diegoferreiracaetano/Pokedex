package com.diegoferreiracaetano.pokedex.ui.screens.otp

import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.pokedex.domain.user.VerifyCodeUseCase
import com.diegoferreiracaetano.pokedex.ui.util.produceUiState
import kotlinx.coroutines.flow.MutableStateFlow

class SendCodeViewModel(
    private val verifyCodeUseCase: VerifyCodeUseCase
): ViewModel() {

    private val _trigger = MutableStateFlow<Pair<String, String>?>(null)

    val uiState = produceUiState(_trigger) {
        verifyCodeUseCase(it)
    }

    fun sendCode(contact: String, code: String) {
        _trigger.value = contact to code
    }
}