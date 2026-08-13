package org.example.project.features.editCoffee.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.project.core.domain.model.Coffee
import org.example.project.core.ui.theme.backgroundColor
import org.example.project.features.editCoffee.store.EditCoffeeScreenModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBottomSheet(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 20.dp),
    screenModel: EditCoffeeScreenModel,
    coffeeId: String,
    onDismissRequest: () -> Unit,
) {
    val bottomSheetState = rememberModalBottomSheetState()

    val state by screenModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        screenModel.loadCoffee(coffeeId)
    }

    ModalBottomSheet(
        modifier = modifier,
        sheetState = bottomSheetState,
        onDismissRequest = onDismissRequest
    ) {
        state.coffee?.let { coffee ->
            EditBottomSheetContent(
                modifier = Modifier
                    .background(backgroundColor)
                    .padding(contentPadding),
                coffee = coffee,
                onSaveClick = { editedCoffee ->
                    screenModel.onSaveClick(editedCoffee)
                    onDismissRequest()
                }
            )
        }
    }
}