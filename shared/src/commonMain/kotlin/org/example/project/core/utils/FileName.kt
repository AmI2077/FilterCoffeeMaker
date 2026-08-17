package org.example.project.core.utils

import kotlin.time.Clock

fun getCoffeeImageName(): String {
    return "coffee_image_${Clock.System.now().toEpochMilliseconds()}.jpg"
}