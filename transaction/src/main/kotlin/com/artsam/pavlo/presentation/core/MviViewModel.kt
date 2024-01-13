@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.artsam.pavlo.presentation.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class MviViewModel<S : MviState, I : MviIntent, E : Effect> : ViewModel() {

    abstract val emptyState: S
    abstract fun handleIntent(intent: I)

    private val _mutableState: MutableStateFlow<S> by lazy { MutableStateFlow(emptyState) }
    val state: StateFlow<S> by lazy { _mutableState.asStateFlow() }

    private val _intentChannel: Channel<E> = Channel()
    val effectStream: Flow<E> = _intentChannel.receiveAsFlow()

    protected val currentState: S
        get() = state.value

    open fun setState(state: S) {
        _mutableState.tryEmit(state)
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun emitEffect(effect: E) {
        viewModelScope.launch(Dispatchers.Main.immediate + exceptionHandler) {
            _intentChannel.send(effect)
        }
    }
}
