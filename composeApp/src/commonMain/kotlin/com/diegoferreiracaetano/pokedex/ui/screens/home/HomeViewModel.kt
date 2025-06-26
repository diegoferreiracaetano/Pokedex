package com.diegoferreiracaetano.pokedex.ui.screens.home

import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.pokedex.data.util.OrderType
import com.diegoferreiracaetano.pokedex.domain.pokedex.PokemonRepository
import com.diegoferreiracaetano.pokedex.domain.user.LoginUseCase
import com.diegoferreiracaetano.pokedex.ui.screens.home.HomeViewModel.FilterConstants.ALL_TYPES
import com.diegoferreiracaetano.pokedex.ui.screens.home.HomeViewModel.FilterConstants.DEFAULT_SEARCH
import com.diegoferreiracaetano.pokedex.ui.util.produceUiState
import com.diegoferreiracaetano.pokedex.util.getLogger
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel(
    private val repository: PokemonRepository
): ViewModel() {

    object FilterConstants {
        const val ALL_TYPES = ""
        const val DEFAULT_SEARCH = ""
    }

    private val _trigger = MutableStateFlow<Triple<String, String, OrderType>?>(null)

    init {
        _trigger.value = Triple(DEFAULT_SEARCH, ALL_TYPES,OrderType.ASC)
    }

    val uiState = produceUiState(_trigger) {
        repository.list(it.first, it.second, it.third)
    }

    fun list(search: String, type: String, order: OrderType) {
        _trigger.value = Triple(search, type, order)
    }
}