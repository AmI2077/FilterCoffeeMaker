package org.example.project.features.addCoffee.ui.vm

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineScope
import org.example.project.core.utils.getCoffeeImageName
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

    fun loadImage(bytes: ByteArray?) {
        bytes?.let { bytes ->
            store.onIntent(
                AddCoffeeIntent.ImagePicked(
                    imageName = getCoffeeImageName(),
                    imageByteArray = bytes
                )
            )
        }
    }

    fun onDialogResult(result: AlreadyExistDialogResult) {
        when(result) {
            AlreadyExistDialogResult.Confirm -> store.onIntent(AddCoffeeIntent.ConfirmAlreadyExistDialog)
            AlreadyExistDialogResult.Dismiss -> store.onIntent(AddCoffeeIntent.DismissAlreadyExistDialog)
        }
    }
}