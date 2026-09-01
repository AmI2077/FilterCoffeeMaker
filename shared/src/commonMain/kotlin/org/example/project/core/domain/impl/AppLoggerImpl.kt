package org.example.project.core.domain.impl

import org.example.project.core.domain.api.AppLogger
import org.example.project.core.domain.api.LogMessageType

class AppLoggerImpl: AppLogger {
    override fun <T> l(
        className: String?,
        type: LogMessageType,
        message: String
    ) {
        println(
            "${type.label}: $className"
        )
        println(message)
    }
}