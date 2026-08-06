package org.example.project.features.coffeeList.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.coffeeScreenTitle
import coffee.shared.generated.resources.ic_add_24
import org.example.project.core.ui.components.AppButton
import org.example.project.core.ui.components.HeaderAppText
import org.example.project.features.coffeeList.ui.components.CoffeeItem
import org.example.project.features.coffeeList.ui.states.CoffeeScreenUiState
import org.example.project.features.coffeeList.ui.vm.CoffeeScreenModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CoffeeScreen(
    modifier: Modifier = Modifier,
    screenModel: CoffeeScreenModel,
    onAddCoffeeClick: () -> Unit,
    onRecipeBtnClick: (coffeeId: Int) -> Unit,
    onItemClick: (coffeeId: Int) -> Unit,
) {
    val state = screenModel.state.collectAsStateWithLifecycle()

    Column(modifier = modifier) {
        CoffeeHeader(
            onAddCoffeeClick = { onAddCoffeeClick() }
        )
        Spacer(Modifier.height(10.dp))
        CoffeeScreenContent(
            state = state.value,
            onItemClick = { coffeeId: Int ->
                onItemClick(coffeeId)
            },
            onLongItemClick = {

            },
            onRecipeBtnClick = { coffeeId ->
                onRecipeBtnClick(coffeeId)
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
fun CoffeeScreenContent(
    modifier: Modifier = Modifier,
    state: CoffeeScreenUiState,
    onItemClick: (coffeeId: Int) -> Unit,
    onLongItemClick: () -> Unit,
    onRecipeBtnClick: (coffeeId: Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(vertical = 10.dp)
    ) {
        items(state.coffeeList) { coffee ->
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
                onLongClick = {
                    onLongItemClick()
                }
            )
        }
    }
}