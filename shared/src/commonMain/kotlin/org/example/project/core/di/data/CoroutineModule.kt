package org.example.project.core.di.data

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.example.project.core.data.impl.AndroidCoroutineDispatchers
import org.example.project.core.domain.api.AppLogger
import org.example.project.core.domain.api.CoroutineDispatchers
import org.example.project.core.domain.api.LogMessageType
import org.example.project.core.domain.exceptions.NullStateException
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val APP_CEN = "AppCoroutineExceptionHandler"

val coroutineModule = module {

    single<CoroutineDispatchers> {
        AndroidCoroutineDispatchers(
            io = Dispatchers.IO,
            main = Dispatchers.Main,
            default = Dispatchers.Default,
            unconfined = Dispatchers.Unconfined
        )
    }

    single(named(APP_CEN)) {
        CoroutineExceptionHandler { _, throwable ->
            when (throwable) {
                is NullStateException -> {
                    get<AppLogger>().l(
                        className = null,
                        type = LogMessageType.ERROR,
                        message = throwable.message!!
                    )
                }

                is Exception -> {
                    get<AppLogger>().l(
                        className = null,
                        type = LogMessageType.ERROR,
                        message = throwable.stackTraceToString()
                    )
                }
            }
        }
    }
}