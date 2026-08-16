package org.example.project.features.addCoffee.ui.vm

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.ismoy.imagepickerkmp.domain.extensions.loadBytes
import io.github.ismoy.imagepickerkmp.features.imagepicker.model.ImagePickerResult
import kotlinx.coroutines.CoroutineScope
import org.example.project.features.addCoffee.store.AddCoffeeIntent
import org.example.project.features.addCoffee.store.AddCoffeeStore
import org.example.project.features.addCoffee.ui.composables.AlreadyExistDialogResult

class AddCoffeeScreenModel(
    storeFactory: (CoroutineScope) -> AddCoffeeStore
) : ScreenModel {
    private val store = storeFactory(screenModelScope)

    val state = store.state

    val uiActions = store.uiActions

    fun pickImage() {
        store.onIntent(AddCoffeeIntent.PickImage)
    }

    fun addCoffee() {
        store.onIntent(AddCoffeeIntent.AddCoffeeBtnClicked(state.value.coffeeInfo!!))
        // TODO "пофиксить nullable coffeeInfo"
    }

    fun loadCoffeeInfo() {
        store.onIntent(AddCoffeeIntent.LoadCoffeeInfo(
            state.value.imageByteArray!!
        ))
        /**
         * TODO "пофиксить nullable imageByteArray"
         * **/
    }

    fun loadPickedImage(
        result: ImagePickerResult
    ) {
        when (result) {
            ImagePickerResult.Dismissed -> Unit
            is ImagePickerResult.Error -> Unit
            ImagePickerResult.Idle -> Unit
            ImagePickerResult.Loading -> Unit
            is ImagePickerResult.Success -> onSuccessImagePickerResult(result)
        }
    }

    private fun onSuccessImagePickerResult(result: ImagePickerResult.Success) {
        val result = result.photos.first()
        val imageName = result.fileName
        val imageByteArray = result.loadBytes()

        store.onIntent(
            AddCoffeeIntent.ImagePicked(
                imageName = imageName,
                imageByteArray = imageByteArray
            )
        )
    }

    fun onDialogResult(result: AlreadyExistDialogResult) {
        when(result) {
            AlreadyExistDialogResult.Confirm -> store.onIntent(AddCoffeeIntent.ConfirmAlreadyExistDialog)
            AlreadyExistDialogResult.Dismiss -> store.onIntent(AddCoffeeIntent.DismissAlreadyExistDialog)
        }
    }

}