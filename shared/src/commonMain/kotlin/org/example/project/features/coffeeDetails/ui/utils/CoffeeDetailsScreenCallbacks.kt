package org.example.project.features.coffeeDetails.ui.utils

interface CoffeeDetailsScreenCallbacks {
    fun onEditBtnClick()
    fun onRecipeBtnClick()
    fun onAddDescriptionBtnClick()
    fun onSaveDescription(desc: String)
    fun onCancellationClick()
}