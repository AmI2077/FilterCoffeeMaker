package org.example.project.core.domain.api

interface ResourceManager {

    suspend fun getFileResource(path: String): String
}