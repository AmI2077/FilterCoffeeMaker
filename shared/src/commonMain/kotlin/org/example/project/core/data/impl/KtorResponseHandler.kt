package org.example.project.core.data.impl

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.util.reflect.TypeInfo
import org.example.project.core.domain.model.NetworkErrors
import org.example.project.core.domain.model.NetworkResult
import org.example.project.core.domain.api.NetworkResponseHandler
import kotlin.reflect.KClass

class KtorResponseHandler: NetworkResponseHandler<HttpResponse> {

    override suspend fun <T : Any> handle(response: HttpResponse, type: KClass<T>): NetworkResult<T> {
        return when (response.status) {
            HttpStatusCode.BadGateway -> NetworkResult.Error(NetworkErrors.BadGateway)
            HttpStatusCode.GatewayTimeout -> NetworkResult.Error(NetworkErrors.GatewayTimeout)
            HttpStatusCode.InternalServerError -> NetworkResult.Error(NetworkErrors.InternalServerError)
            HttpStatusCode.OK -> {
                try {
                    NetworkResult.Success(response.body(TypeInfo(type)))
                } catch (_: Exception) {
                    NetworkResult.Error(NetworkErrors.UnknownError)
                }
            }
            else -> {
                NetworkResult.Error(NetworkErrors.UnknownError)
            }
        }
    }
}
