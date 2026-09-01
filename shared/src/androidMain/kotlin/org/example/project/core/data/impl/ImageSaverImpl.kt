package org.example.project.core.data.impl

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.exifinterface.media.ExifInterface
import org.example.project.core.domain.api.ImageSaver
import java.io.ByteArrayInputStream
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

                // 1. Декодируем байты в Bitmap
                val originalBitmap = BitmapFactory.decodeByteArray(fileBytes, 0, fileBytes.size)

                // 2. Читаем EXIF из байт (нужен InputStream)
                val inputStream = ByteArrayInputStream(fileBytes)
                val exif = ExifInterface(inputStream)
                val orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL
                )

                // 3. Определяем угол поворота
                val rotationAngle = when (orientation) {
                    ExifInterface.ORIENTATION_ROTATE_90 -> 90f
                    ExifInterface.ORIENTATION_ROTATE_180 -> 180f
                    ExifInterface.ORIENTATION_ROTATE_270 -> 270f
                    else -> 0f
                }

                println("ROTATION_ANGLE: $rotationAngle")

                if (rotationAngle != 0f) {
                    // 4. Поворачиваем Bitmap
                    val matrix = Matrix().apply { postRotate(rotationAngle) }
                    val rotatedBitmap = Bitmap.createBitmap(
                        originalBitmap, 0, 0,
                        originalBitmap.width, originalBitmap.height,
                        matrix, true
                    )

                    // 5. Записываем уже повернутый Bitmap в файл
                    imageFile.outputStream().use { outputStream ->
                        rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    }

                    originalBitmap.recycle()
                    rotatedBitmap.recycle()
                } else {
                    // Если поворот не нужен, сохраняем исходные байты
                    imageFile.writeBytes(fileBytes)
                }

                imageFile.absolutePath
            } catch (e: Exception) {
                println("IMAGE_ANDROID_SAVER_LOG: ${e.message}")
                null
            }
        }
}