package org.example.project.features.addCoffee.data

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import org.example.project.core.domain.api.ImageSaver
import platform.Foundation.NSData
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask
import platform.Foundation.dataWithBytes
import platform.Foundation.writeToURL

actual class ImageSaverImpl : ImageSaver {
    actual override suspend fun getDirectory(fileName: String): String? {
        return try {
            val paths = NSSearchPathForDirectoriesInDomains(
                NSDocumentDirectory,
                NSUserDomainMask,
                true
            )
            val filePath = "${paths.first()}/fileName"
            filePath
        } catch (e: Exception) {
            println("IMAGE_IOS_SAVER_LOG: ${e.message}")
            null
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    actual override suspend fun saveImage(name: String, fileBytes: ByteArray): String? {

        return try {
            val fileManager = NSFileManager.defaultManager
            val urls = fileManager.URLsForDirectory(NSDocumentDirectory, NSUserDomainMask)
            val documentDirectory = urls.first() as NSURL

            val fileURL = documentDirectory.URLByAppendingPathComponent(name)

            val nsData = fileBytes.usePinned { pinned ->
                NSData.dataWithBytes(pinned.addressOf(0), fileBytes.size.toULong())
            }

            if (fileURL != null && nsData.writeToURL(fileURL, true)) {
                fileURL.path
            } else {
                null
            }
        } catch (e: Exception) {
            println("IMAGE_IOS_SAVER_LOG: ${e.message}")
            null
        }
    }
}