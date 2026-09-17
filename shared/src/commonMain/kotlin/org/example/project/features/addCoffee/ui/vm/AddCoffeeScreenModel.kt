package org.example.project.features.addCoffee.ui.vm

import kotlinx.coroutines.CoroutineScope
import org.example.project.core.data.impl.BaseScreenModel
import org.example.project.core.utils.getCoffeeImageName
import org.example.project.features.addCoffee.store.AddCoffeeIntent
import org.example.project.features.addCoffee.store.AddCoffeeStore
import org.example.project.features.addCoffee.ui.composables.AlreadyExistDialogResult

class AddCoffeeScreenModel(
    storeFactory: (CoroutineScope) -> AddCoffeeStore
) : BaseScreenModel() {
    private val store = storeFactory(screenModelScopeWithHandler)

    val state = store.state

    val uiActions = store.uiActions

    fun pickImage() {
        store.onIntent(AddCoffeeIntent.PickImage)
    }

    fun addCoffee() {
        store.onIntent(AddCoffeeIntent.AddCoffeeBtnClicked)
    }

    fun loadCoffeeInfo() {
        store.onIntent(AddCoffeeIntent.LoadCoffeeInfo)
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