package org.example.project.core.domain.api

interface AppLogger {

    fun <T> l(
        className: String?,
        type: LogMessageType,
        message: String
    )
}

inline fun <reified T: Any> AppLogger.log(
    type: LogMessageType,
    message: String,
) {
    l<T>(
        className = T::class.simpleName,
        type = type,
        message = message
    )
}

enum class LogMessageType(val label: String) {
    INFO("INFO_MESSAGE"),
    ERROR("ERROR_MESSAGE"),
}