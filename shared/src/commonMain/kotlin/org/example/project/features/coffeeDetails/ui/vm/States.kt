package org.example.project.features.coffeeDetails.ui.vm

import org.example.project.core.domain.model.Coffee

data class CoffeeDetailsScreenUiState(
    val content: Coffee? = null,
    val showEditDescriptionField: Boolean = false,
    val showEditBottomSheet: Boolean = false,
)

sealed interface CoffeeDetailsIntent {
    data class LoadCoffeeDetails(val coffeeId: String): CoffeeDetailsIntent
    data object RecipeBtnClicked: CoffeeDetailsIntent
    data object EditBtnClicked: CoffeeDetailsIntent
    data object DismissEditBottomSheet: CoffeeDetailsIntent
    data object AddDescriptionBtnClicked: CoffeeDetailsIntent
    data class SaveDescriptionBtnClicked(val description: String): CoffeeDetailsIntent
    data object CancelDescriptionBtnClicked: CoffeeDetailsIntent
}

sealed interface CoffeeDetailsAction {
    data object ClickOnRecipeBtn: CoffeeDetailsAction
}