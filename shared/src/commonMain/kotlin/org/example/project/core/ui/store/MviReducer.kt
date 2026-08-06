package org.example.project.core.ui.store

/**
 * @param S type of state for ui
 * @param R type of result
 * @see org.example.project.core.ui.store.MviStore
 * */
interface MviReducer<S, R> {

    /**
     *  Create new object of state based on the previous state and result.
     *  @param oldState current StateFlow
     *  @param result result of user intent
     *
     *  @return new state
     * **/
    fun reduce(oldState: S, result: R): S
}