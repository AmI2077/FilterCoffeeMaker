package org.example.project.core.data.impl

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import org.example.project.core.di.data.APP_CEN
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

abstract class BaseScreenModel: ScreenModel, KoinComponent {
    private val handler: CoroutineExceptionHandler by inject(named(APP_CEN))

    protected val screenModelScopeWithHandler
        get() = CoroutineScope(screenModelScope.coroutineContext + handler)
}