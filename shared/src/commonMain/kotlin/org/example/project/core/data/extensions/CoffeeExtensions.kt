package org.example.project.core.data.extensions

import org.example.project.core.domain.api.ImageSaver
import org.example.project.core.domain.model.Coffee

suspend fun Coffee.getWithImageDirectory(
    imageSaver: ImageSaver
): Coffee {
    checkNotNull(imagePath) {
        return this
    }
    val directory = imageSaver.getDirectory(imagePath)
    return this.copy(
        imagePath = directory
    )
}