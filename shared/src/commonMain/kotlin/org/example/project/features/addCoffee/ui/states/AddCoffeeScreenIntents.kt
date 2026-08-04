package org.example.project.features.addCoffee.ui.states

import io.github.ismoy.imagepickerkmp.features.imagepicker.model.ImagePickerResult

sealed interface AddCoffeeScreenIntents {

    data object PickImage : AddCoffeeScreenIntents
    data class ImagePicked(val result: ImagePickerResult) : AddCoffeeScreenIntents
    data object LoadImage : AddCoffeeScreenIntents
    data object AddCoffee : AddCoffeeScreenIntents
}