package com.diegoferreiracaetano.pokedex.ui.screens.account

import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.pokedex.domain.user.CreateAccountStepType
import com.diegoferreiracaetano.pokedex.domain.user.CreateAccountUseCase
import com.diegoferreiracaetano.pokedex.ui.util.produceUiState
import kotlinx.coroutines.flow.MutableStateFlow

class CreateAccountViewModel(
    private val createUserUseCase: CreateAccountUseCase
): ViewModel() {

    private val _trigger = MutableStateFlow<Pair<CreateAccountStepType, String>?>(null)

    val uiState = produceUiState(_trigger) {
        createUserUseCase(it)
    }

    fun saveStep(step: CreateAccountStepType, value: String) {
        _trigger.value = step to value
    }
}