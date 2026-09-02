package org.example.project.core.domain.impl

import org.example.project.core.domain.api.ImageSaver
import org.example.project.core.domain.exceptions.NullStateException
import org.example.project.core.domain.model.Coffee
import kotlin.reflect.KProperty0

/**
 * Этот метод для безопасного обращения к nullable полям, например,
 * если они не могут быть nullable по логике программы в момент выполнения
 *
 * @throws org.example.project.core.domain.exceptions.NullStateException
 */
inline fun <S: Any> runIfExist(
    info: KProperty0<S?>,
    action: (S) -> Unit,
) {
    val value = info.get()

    if (value != null) {
        action(value)
    } else {
        throw NullStateException("Field ${info.name} from state doesn't exist")
    }
}

suspend fun Coffee.getWithImageDirectory(
    imageSaver: ImageSaver
): Coffee {
    return imagePath?.let {
        val directory = imageSaver.getDirectory(imagePath)
        this.copy(
            imagePath = directory
        )
    } ?: this
}