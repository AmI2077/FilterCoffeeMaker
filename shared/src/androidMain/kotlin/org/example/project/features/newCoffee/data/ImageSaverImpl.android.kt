package org.example.project.features.newCoffee.data

import android.content.Context
import org.example.project.core.domain.api.ImageSaver
import java.io.File

actual class ImageSaverImpl(private val context: Context) : ImageSaver {
    actual override suspend fun getDirectory(fileName: String): String? {
        return try {
            val filePath = "${context.filesDir}/$fileName"
            filePath
        } catch (e: Exception) {
            println("IMAGE_ANDROID_SAVER_LOG: ${e.message}")
            null
        }
    }

    actual override suspend fun saveImage(name: String, fileBytes: ByteArray): String? {
        return try {
            val directory = context.filesDir
            val imageFile = File(directory, name)
            imageFile.writeBytes(fileBytes)
            imageFile.absolutePath
        } catch (e: Exception) {
            println("IMAGE_ANDROID_SAVER_LOG: ${e.message}")
            null
        }
    }
}