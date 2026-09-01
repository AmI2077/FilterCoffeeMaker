package org.example.project.core.data.impl

import org.example.project.core.domain.api.ImageSaver

expect class ImageSaverImpl : ImageSaver {
    override suspend fun getDirectory(fileName: String): String?
    override suspend fun saveImage(name: String, fileBytes: ByteArray): String?
}