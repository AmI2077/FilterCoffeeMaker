package org.example.project.features.editCoffee.ui.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBottomSheet(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 20.dp),
    show: Boolean,
    onDismissRequest: () -> Unit
) {
    val state = rememberModalBottomSheetState()

    if (show) {
        ModalBottomSheet(
            modifier = modifier,
            sheetState = state,
            onDismissRequest = onDismissRequest
        ) {
            EditBottomSheetContent(
                modifier = Modifier
                    .padding(contentPadding)
            )
        }
    }
}