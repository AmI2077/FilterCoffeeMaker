package org.example.project.core.domain.model

sealed interface Result<out T> {
    data class Content<T>(val data: T) : Result<T>
    data class Error(val message: String) : Result<Nothing>
}