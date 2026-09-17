package org.example.project.core.domain.api

import org.example.project.core.domain.model.NetworkResult
import kotlin.reflect.KClass

interface NetworkResponseHandler<R> {
    suspend fun <T : Any> handle(response: R, type: KClass<T>): NetworkResult<T>
}