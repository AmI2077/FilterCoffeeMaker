package org.example.project.core.ui.store

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

fun <S, R>MutableStateFlow<S>.updateStateWithReducer(reducer: MviReducer<S, R>, result: R) {
    update { oldState ->
        reducer.reduce(oldState, result)
    }
}

fun <A>MutableSharedFlow<A>.emitAction(scope: CoroutineScope, action: A) {
    scope.launch { emit(action) }
}