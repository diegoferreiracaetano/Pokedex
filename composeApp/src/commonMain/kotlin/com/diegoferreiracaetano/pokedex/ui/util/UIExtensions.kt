package com.diegoferreiracaetano.pokedex.ui.util

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

@Immutable
data class UiState<out T>(
    val success: T? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

fun <T, R> Flow<T?>.asUiState(
    scope: CoroutineScope,
    initialState: UiState<R> = UiState(),
    transform: (T) -> Flow<R>
): StateFlow<UiState<R>> {
    return this
        .filterNotNull()
        .flatMapLatest { trigger ->
            transform(trigger)
                .map { result ->
                    UiState(success = result)
                }
                .onStart { emit(UiState(isLoading = true)) }
                .catch { e ->
                    emit(UiState(error = e.message ?: "Ocorreu um erro desconhecido"))
                }
        }
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = initialState
        )
}

fun <T, R> ViewModel.produceUiState(
    trigger: Flow<T?>,
    initialState: UiState<R> = UiState(),
    transform: (T) -> Flow<R>
): StateFlow<UiState<R>> {
    return trigger.asUiState(
        scope = this.viewModelScope,
        initialState = initialState,
        transform = transform
    )
}


//fun <T, S> Flow<T?>.asUiState(
//    scope: CoroutineScope,
//    initialState: S,
//    loadingState: S,
//    started: SharingStarted = SharingStarted.WhileSubscribed(5000L),
//    transform: (T) -> Flow<S>
//): StateFlow<S> {
//    return this
//        .filterNotNull()
//        .flatMapLatest { value ->
//            transform(value).onStart { emit(loadingState) }
//        }
//        .stateIn(
//            scope = scope,
//            started = started,
//            initialValue = initialState
//        )
//}

//fun <T, R> Flow<T?>.asUiState(
//    scope: CoroutineScope,
//    started: SharingStarted = SharingStarted.WhileSubscribed(5000L),
//    transform: suspend (T) -> R
//): StateFlow<UiState<R>> {
//    return this
//        .filterNotNull()
//        .flatMapLatest { trigger ->
//            flow {
//                try {
//                    val result = transform(trigger)
//                    emit(UiState.Success(result))
//                } catch (e: Exception) {
//                    emit(UiState.Error(e))
//                }
//            }
//                .onStart { emit(UiState.Loading) }
//        }
//        .stateIn(
//            scope = scope,
//            started = started,
//            initialValue = UiState.Loading
//        )
//}


//sealed interface UiState<out T> {
//    data class Success<T>(val value: T) : UiState<T>
//    data object Loading : UiState<Nothing>
//    data class Error(val throwable: Throwable? = null) : UiState<Nothing>
//}