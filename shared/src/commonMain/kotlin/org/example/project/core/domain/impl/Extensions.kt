package org.example.project.core.domain.impl

import org.example.project.core.domain.api.AppLogger
import org.example.project.core.domain.api.ImageSaver
import org.example.project.core.domain.api.LogMessageType
import org.example.project.core.domain.api.log
import org.example.project.core.domain.model.Coffee
import kotlin.reflect.KProperty0

/**
 * Этот метод для безопасного обращения к nullable полям, например,
 * если они не могут быть nullable по логике программы в момент выполнения
 */
inline fun <S: Any, reified T: Any> T.runIfExist(
    info: KProperty0<S?>,
    logger: AppLogger? = null,
    action: (S) -> Unit,
) {
    val value = info.get()

    if (value != null) {
        action(value)
    } else {
        logger?.log<T>(
            type = LogMessageType.ERROR,
            message = "Field ${info.name} from state doesn't exist"
        )
    }
}

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