package org.example.project.core.data.network.dto

sealed interface NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>
    data class Error(val error: NetworkErrors) : NetworkResult<Nothing>
}

sealed class NetworkErrors(val message: String) {
    data object InternalServerError : NetworkErrors(INTERNAL_SERVER_ERROR)
    data object BadGateway : NetworkErrors(BAD_GATEWAY)
    data object GatewayTimeout : NetworkErrors(GATEWAY_TIMEOUT)
    data object UnknownError : NetworkErrors(UNKNOWN_ERROR)


    companion object {
        private const val INTERNAL_SERVER_ERROR = "Похоже нет интернета, проверь подключение"
        private const val BAD_GATEWAY = "Неполадки на сервере, уже чиню"
        private const val GATEWAY_TIMEOUT = "Сервер не отвечает, попробуй позже"
        private const val UNKNOWN_ERROR = "Нейронка не смогла, попробуй еще раз"
    }
}

