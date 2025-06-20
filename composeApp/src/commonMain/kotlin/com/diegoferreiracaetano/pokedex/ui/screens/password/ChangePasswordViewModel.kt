package com.diegoferreiracaetano.pokedex.ui.screens.password

import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.pokedex.ui.util.produceUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

class ChangePasswordViewModel: ViewModel() {

    private val _trigger = MutableStateFlow<String?>(null)

    val uiState = produceUiState(_trigger) {
        // todo change password

        flow {
            emit(true)
        }
    }

    fun changePassword(newPassword: String) {
        _trigger.value = newPassword
    }
}