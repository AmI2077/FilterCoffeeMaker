package org.example.project.core.utils

import kotlin.time.Clock

// TODO "Перенести в domain/impl/extensions"

fun getCoffeeImageName(): String {
    return "coffee_image_${Clock.System.now().toEpochMilliseconds()}.jpg"
}