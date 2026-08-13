package org.example.project.features.savedCoffee.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.coffeeScreenTitle
import coffee.shared.generated.resources.ic_add_24
import org.example.project.core.domain.model.Coffee
import org.example.project.core.ui.components.AppButton
import org.example.project.core.ui.components.AppDialog
import org.example.project.core.ui.components.HeaderAppText
import org.example.project.features.savedCoffee.store.SavedCoffeeScreenActions
import org.example.project.features.savedCoffee.store.SavedCoffeeScreenModel
import org.example.project.features.savedCoffee.store.SavedCoffeeScreenUiState
import org.example.project.features.savedCoffee.ui.components.CoffeeItem
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

sealed interface SavedCoffeeDialogResult {
    data class Confirm(val coffee: Coffee) : SavedCoffeeDialogResult
    data object Dismiss : SavedCoffeeDialogResult
}

@Composable
fun SavedCoffeeScreen(
    modifier: Modifier = Modifier,
    screenModel: SavedCoffeeScreenModel,
    onAddCoffeeClick: () -> Unit,
    onRecipeBtnClick: (coffeeId: String) -> Unit,
    onItemClick: (coffeeId: String) -> Unit,
) {
    val state by screenModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        screenModel.uiActions.collect { action ->
            when(action) {
                is SavedCoffeeScreenActions.CoffeeItemClicked -> {
                    onItemClick(action.coffeeId)
                }
                is SavedCoffeeScreenActions.RecipeBtnClicked -> {
                    onRecipeBtnClick(action.coffeeId)
                }
            }
        }
    }
    val showDialog = state.showDialog

    if (showDialog != null) {
        AppDialog(
            message = "Вы хотите удалить ${showDialog.coffee.title}?\n\nВсе рецепты с этим кофе также будут удалены.",
            onConfirmClick = { screenModel.onDialogResult(SavedCoffeeDialogResult.Confirm(showDialog.coffee)) },
            onDismissClick = { screenModel.onDialogResult(SavedCoffeeDialogResult.Dismiss) }
        )
    }

    Column(modifier = modifier) {
        CoffeeHeader(
            onAddCoffeeClick = { onAddCoffeeClick() }
        )
        Spacer(Modifier.height(10.dp))
        SavedCoffeeScreenContent(
            state = state,
            onItemClick = { coffeeId: String ->
                screenModel.onCoffeeItemClick(coffeeId)
            },
            onLongItemClick = { coffee: Coffee ->
                screenModel.onCoffeeItemLongClick(coffee)
            },
            onRecipeBtnClick = { coffeeId ->
                screenModel.onRecipeBtnClick(coffeeId)
            }
        )
    }
}

@Composable
fun CoffeeHeader(
    modifier: Modifier = Modifier,
    onAddCoffeeClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HeaderAppText(
            text = stringResource(Res.string.coffeeScreenTitle),
            fontSize = 46.sp,
        )
        Spacer(Modifier.weight(1f))
        AppButton(
            modifier = modifier.size(50.dp),
            icon = {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(Res.drawable.ic_add_24),
                    contentDescription = null,
                )
            },
            text = null
        ) {
            onAddCoffeeClick()
        }
    }
}

@Composable
fun SavedCoffeeScreenContent(
    modifier: Modifier = Modifier,
    state: SavedCoffeeScreenUiState,
    onItemClick: (coffeeId: String) -> Unit,
    onLongItemClick: (coffee: Coffee) -> Unit,
    onRecipeBtnClick: (coffeeId: String) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(vertical = 10.dp)
    ) {
        items(state.savedCoffee) { coffee ->
            CoffeeItem(
                modifier = Modifier
                    .fillMaxWidth(),
                coffee = coffee,
                onRecipeBtnClick = { coffeeId ->
                    onRecipeBtnClick(coffeeId)
                },
                onClick = { coffeeId ->
                    onItemClick(coffeeId)
                },
                onLongClick = { coffee ->
                    onLongItemClick(coffee)
                }
            )
        }
    }
}