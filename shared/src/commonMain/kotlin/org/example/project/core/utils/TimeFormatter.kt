package org.example.project.core.utils

/**
 * Extension function for convert int-value to time string with template "mm:ss"
 * ### Sample use-case
 * ```kotlin
 * val timeString = 180.toTimeString()
 * // timeString = "03:00"
 * ```
 *
 *@return "mm:ss" string
 *@see org.example.project.core.utils
 *
 **/

// TODO "Перенести в domain/impl/extensions"

fun Int.toTimeString(): String {
    val minutes = this / 60
    val seconds = this % 60

    val result = "${minutes.toString().padStart(2, '0')}:" +
            seconds.toString().padStart(2, '0')
    return result
}
