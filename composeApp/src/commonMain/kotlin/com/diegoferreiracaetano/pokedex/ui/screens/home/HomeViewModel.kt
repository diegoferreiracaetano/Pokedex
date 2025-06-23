package com.diegoferreiracaetano.pokedex.ui.screens.home

import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.pokedex.domain.pokedex.PokemonRepository
import com.diegoferreiracaetano.pokedex.domain.user.LoginUseCase
import com.diegoferreiracaetano.pokedex.ui.util.produceUiState
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel(
    private val repository: PokemonRepository
): ViewModel() {

    private val _trigger = MutableStateFlow<String?>(null)

    val uiState = produceUiState(_trigger) {
        repository.list()
    }

    fun list(search: String) {
        _trigger.value = search
    }
}