package org.example.project.features.addCoffee.data

import org.example.project.core.domain.api.ImageSaver

expect class ImageSaverImpl : ImageSaver {
    override suspend fun getDirectory(fileName: String): String?
    override suspend fun saveImage(name: String, fileBytes: ByteArray): String?
}