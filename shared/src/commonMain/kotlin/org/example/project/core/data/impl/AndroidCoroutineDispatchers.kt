package org.example.project.core.data.impl

import kotlinx.coroutines.CoroutineDispatcher
import org.example.project.core.domain.api.CoroutineDispatchers

class AndroidCoroutineDispatchers(
    private val io: CoroutineDispatcher,
    private val main: CoroutineDispatcher,
    private val default: CoroutineDispatcher,
    private val unconfined: CoroutineDispatcher
): CoroutineDispatchers {

    override fun io(): CoroutineDispatcher = io

    override fun default(): CoroutineDispatcher = default

    override fun main(): CoroutineDispatcher = main

    override fun unconfined(): CoroutineDispatcher = unconfined
}