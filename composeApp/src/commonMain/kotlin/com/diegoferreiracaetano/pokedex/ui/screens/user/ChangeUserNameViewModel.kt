package com.diegoferreiracaetano.pokedex.ui.screens.user

import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.pokedex.ui.util.produceUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class ChangeUserNameViewModel: ViewModel() {

    private val _trigger = MutableStateFlow<Pair<String, String>?>(null)

    val uiState = produceUiState(_trigger) {
        // todo change user name

        flow {

            emit(true)
            
            throw Throwable("erros")
        }
    }

    fun changeUserName(userName: String, fullName: String) {
        _trigger.value = userName to fullName
    }
}