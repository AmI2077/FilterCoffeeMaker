package org.example.project.features.coffeeDetails.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.project.core.domain.model.Coffee
import org.example.project.core.ui.components.AppOutlinedButton
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.getComfortaBold
import org.example.project.core.ui.theme.getComfortaRegular
import org.example.project.features.addCoffee.ui.composables.CoffeeBalance
import org.example.project.features.coffeeDetails.ui.vm.CoffeeDetailsAction
import org.example.project.features.coffeeDetails.ui.vm.CoffeeDetailsIntent
import org.example.project.features.coffeeDetails.ui.vm.CoffeeDetailsScreenModel
import org.example.project.features.editCoffee.ui.composables.EditBottomSheet

@Composable
fun CoffeeDetailsScreen(
    modifier: Modifier = Modifier,
    coffeeId: String,
    screenModel: CoffeeDetailsScreenModel,
    onRecipeBtnClick: (coffeeId: String) -> Unit
) {
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        screenModel.onIntent(CoffeeDetailsIntent.LoadCoffeeDetails(coffeeId))
    }

    LaunchedEffect(Unit) {
        screenModel.uiActions.collect { action ->
            when(action) {
                CoffeeDetailsAction.ClickOnRecipeBtn -> onRecipeBtnClick(coffeeId)
            }
        }
    }

    val state by screenModel.state.collectAsStateWithLifecycle()

    state.content?.let { coffee ->
        if (state.showEditBottomSheet) {
            EditBottomSheet(
                show = true,
                onDismissRequest = {
                    screenModel.onIntent(CoffeeDetailsIntent.DismissEditBottomSheet)
                }
            )
        }
        CoffeeDetailsScreenContent(
            modifier = modifier
                .verticalScroll(scrollState),
            coffee = coffee,
            onRecipeBtnClick = { onRecipeBtnClick(coffeeId) },
            onEditBtnClick = {
                screenModel.onIntent(CoffeeDetailsIntent.EditBtnClicked)
            },
            onAddDescriptionBtnClick = { screenModel.onIntent(CoffeeDetailsIntent.AddDescriptionBtnClicked) },
            showEditDescriptionField = state.showEditDescriptionField,
            onSaveDescription = { desc ->
                screenModel.onIntent(CoffeeDetailsIntent.SaveDescriptionBtnClicked(desc))
            },
            onCancellationClick = {
                screenModel.onIntent(CoffeeDetailsIntent.CancelDescriptionBtnClicked)
            }
        )
    }
}

@Composable
fun CoffeeDetailsScreenContent(
    modifier: Modifier,
    coffee: Coffee,
    showEditDescriptionField: Boolean,
    onEditBtnClick: () -> Unit,
    onRecipeBtnClick: () -> Unit,
    onAddDescriptionBtnClick: () -> Unit,
    onSaveDescription: (String) -> Unit,
    onCancellationClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        CoffeeImageWithQGrade(
            model = coffee.imagePath,
            qGrade = coffee.qGrade
        )
        RoastingAndProcessingRow(
            roasting = coffee.roasting,
            processingMethod = coffee.processingMethod
        )
        Spacer(Modifier.height(5.dp))
        RegularAppText(
            text = coffee.title,
            fontSize = 30.sp,
            fontFamily = getComfortaBold()
        )
        Spacer(Modifier.height(5.dp))
        RegularAppText(
            text = coffee.tasteDescription,
            maxLines = Int.MAX_VALUE,
            fontSize = 20.sp,
            fontFamily = getComfortaRegular()
        )
        Spacer(Modifier.height(20.dp))
        CoffeeBalance(
            density = coffee.density,
            acidity = coffee.acidity
        )
        Spacer(Modifier.height(5.dp))
        if (showEditDescriptionField) {
            EditDescriptionView(
                onSaveBtnClick = { onSaveDescription(it) },
                onCancellationClick = onCancellationClick,
            )
        } else {
            DescriptionView(
                description = coffee.userDescription,
                onAddDescriptionBtnClick = onAddDescriptionBtnClick
            )
        }
        CoffeeDetailsButtons(
            modifier = Modifier.fillMaxWidth(),
            onRecipeBtnClick = onRecipeBtnClick,
            onEditBtnClick = onEditBtnClick
        )
    }
}

@Composable
private fun DescriptionView(
    description: String?,
    onAddDescriptionBtnClick: () -> Unit,
) {
    if (description.isNullOrBlank()) {
        AppOutlinedButton(
            text = "Добавить описание +",
            onClick = { onAddDescriptionBtnClick() }
        )
        Spacer(Modifier.height(40.dp))
    } else {
        UserDescription(
            description = description
        )
    }
}