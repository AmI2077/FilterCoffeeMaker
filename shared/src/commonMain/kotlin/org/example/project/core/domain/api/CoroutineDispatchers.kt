package org.example.project.core.domain.api

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchers {

    fun io(): CoroutineDispatcher
    fun default(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
    fun unconfined(): CoroutineDispatcher
}
