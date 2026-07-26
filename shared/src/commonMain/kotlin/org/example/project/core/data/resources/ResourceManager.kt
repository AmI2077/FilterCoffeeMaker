package org.example.project.core.data.resources

interface ResourceManager {

    suspend fun getFileResource(path: String): String
}