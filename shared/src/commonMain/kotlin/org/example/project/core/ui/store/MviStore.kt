package org.example.project.core.ui.store

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Interface that describes MviStore that keeps business logic and
 * immutable state for ui
 *
 * @param S type of state for ui
 * @param I type of user intent
 * @param A type of ui one-time actions
 *
 * @see org.example.project.core.ui.store.MviReducer
 * */
interface MviStore<S, I, A> {
    /**
     * Immutable state for ui
     * */
    val state: StateFlow<S>

    /**
     * One-time ui actions
     * */
    val uiActions: SharedFlow<A>

    /**
     * Function that processes user intents coming from ViewModel
     *
     * @param intent user intent
     * */
    fun onIntent(intent: I)
}