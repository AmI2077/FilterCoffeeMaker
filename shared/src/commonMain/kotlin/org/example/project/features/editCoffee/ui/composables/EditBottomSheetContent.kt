package org.example.project.features.editCoffee.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.core.domain.model.Coffee
import org.example.project.core.domain.model.mockCoffee
import org.example.project.core.domain.model.processingMethods
import org.example.project.core.domain.model.roastingTypes
import org.example.project.core.ui.components.AppDropdownMenu
import org.example.project.core.ui.components.AppOutlinedTextField
import org.example.project.core.ui.components.AppSlider
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.blueGrayText
import org.example.project.core.ui.theme.white

@Preview
@Composable
fun EditBottomSheetContent(
    modifier: Modifier = Modifier,
    coffee: Coffee = mockCoffee
) {
    var editableCoffee by remember { mutableStateOf(coffee) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        RegularAppText(
            text = "Редактирование",
            fontSize = 24.sp
        )
        Spacer(Modifier.height(10.dp))
        AppOutlinedTextField(
            text = editableCoffee.title,
            label = "Введи название",
            onTextChange = { newTitle ->
                editableCoffee = editableCoffee.copy(title = newTitle)
            }
        )
        AppOutlinedTextField(
            text = editableCoffee.qGrade ?: "",
            label = "Введи QGrade",
            onTextChange = { newTitle ->
                editableCoffee = editableCoffee.copy(title = newTitle)
            }
        )
        AppDropdownMenu(
            value = editableCoffee.roasting,
            values = roastingTypes,
            label = "Обжарка"
        )
        AppDropdownMenu(
            value = editableCoffee.processingMethod,
            values = processingMethods,
            label = "Обработка"
        )
        RegularAppText(
            text = "Кислотность",
            fontSize = 16.sp,
            color = blueGrayText
        )
        AppSlider()
        RegularAppText(
            text = "Плотность",
            fontSize = 16.sp,
            color = blueGrayText
        )
        AppSlider()
        AppOutlinedTextField(
            text = editableCoffee.userDescription ?: "",
            label = "Введи описание",
            onTextChange = { newTitle ->
                editableCoffee = editableCoffee.copy(title = newTitle)
            }
        )
    }
}