package org.example.project.core.domain.api

interface ImageSaver {

    suspend fun getDirectory(fileName: String): String?

    suspend fun saveImage(name: String, fileBytes: ByteArray): String?
}