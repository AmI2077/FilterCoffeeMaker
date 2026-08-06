package org.example.project.features.addCoffee.store

import org.example.project.core.domain.model.Coffee

/**
 *  Ui state for AddCoffeeScreen
 *
 *  @property imageDirectory путь для отображения выбранного фото из галереи
 *  @property imageName имя файла для сохранения в бд
 *  @property imageByteArray массив для отправки фото в нейронку
 *  @property isLoading когда true то отображается loader
 *  @property error сообщение об ошибке
 *  @property coffeeInfo информация о кофе полученная с фото
 * */
@Suppress("ArrayInDataClass")
data class AddCoffeeScreenUiState(
    val imageDirectory: String? = null,
    val imageByteArray: ByteArray? = null,
    val imageName: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val coffeeInfo: Coffee? = null,
)

/**
 * Пользовательские интенты, отправляемые из [org.example.project.features.addCoffee.ui.vm.AddCoffeeScreenModel].
 */
@Suppress("ArrayInDataClass")
sealed interface AddCoffeeIntent {

    /**
     *  Отправляется когда пользователь нажимает на плейсхолдер для открытия галереи
     */
    data object PickImage : AddCoffeeIntent

    /**
     * Отправляется когда пользователь выбрал изображение и галерея закрылась
     *
     * @property imageName имя выбранного файла.
     * @property imageByteArray массив байтов фотографии.
     */
    data class ImagePicked(
        val imageName: String?,
        val imageByteArray: ByteArray,
    ) : AddCoffeeIntent

    /**
     * Отправляется когда пользователь нажал кнопку "Добавить"
     *
     * @property coffeeInfo передается для сохранения информации о кофе в бд.
     */
    data class AddCoffee(val coffeeInfo: Coffee) : AddCoffeeIntent

    /**
     * Отправляется когда пользователь нажимает кнопку "Загрузить" после выбора фото.
     *
     * @property imageByteArray передается для отправки в нейронку.
     */
    data class LoadCoffeeInfo(val imageByteArray: ByteArray) : AddCoffeeIntent
}

/**
 *  Результаты, которые получаются после обработки интентов в store.
 *  Нужны для изменения [AddCoffeeScreenUiState].
 *
 *  Передаются в reducer для вычисления состояния
 */
@Suppress("ArrayInDataClass")
sealed interface AddCoffeeResults {

    /**
     * Обозначает состояние загрузки.
     */
    data object Loading : AddCoffeeResults

    /**
     * Обозначает состояние когда изображение успешно загружено из галереи.
     *
     * @property imageByteArray массив для отправки в нейронку.
     * @property imageDirectory путь к файлу для отображения в ui.
     * @property imageName имя файла для сохранения в бд.
     */
    data class ImageLoaded(
        val imageByteArray: ByteArray,
        val imageDirectory: String?,
        val imageName: String?
    ) : AddCoffeeResults

    /**
     * Обозначает успешное прочтение информации с фотографии.
     *
     * @property coffeeInfo the parsed coffee information returned by the data layer.
     */
    data class CoffeeInfoSuccess(val coffeeInfo: Coffee) : AddCoffeeResults

    /**
     * Обозначает ошибку при чтении изображения.
     *
     * @property message the error message describing what went wrong.
     */
    data class CoffeeInfoError(val message: String) : AddCoffeeResults
}

/**
 * События, которые отправляются в ui один раз.
 */
sealed interface AddCoffeeActions {

    /**
     * Эмитится когда пользователь нажимает на иконку для выбора фото.
     */
    data object OpenGallery : AddCoffeeActions

    /**
     * Эмитится когда пользователь нажимает на кнопку "Добавить"
     */
    data object AddCoffeeBtnClicked : AddCoffeeActions
}
