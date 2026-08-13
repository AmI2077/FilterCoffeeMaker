package org.example.project.features.editCoffee.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.core.domain.model.Coffee
import org.example.project.core.domain.model.mockCoffee
import org.example.project.core.domain.model.processingMethods
import org.example.project.core.domain.model.roastingTypes
import org.example.project.core.ui.components.AppButton
import org.example.project.core.ui.components.AppDropdownMenu
import org.example.project.core.ui.components.AppOutlinedTextField
import org.example.project.core.ui.components.AppSlider
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.lightGray
import org.example.project.core.ui.theme.textSecondaryColor
import org.example.project.core.ui.theme.getComfortaBold
import org.example.project.core.ui.theme.white

@Composable
fun EditBottomSheetContent(
    modifier: Modifier = Modifier,
    coffee: Coffee = mockCoffee,
    onSaveClick: (editCoffee: Coffee) -> Unit
) {
    var editableCoffee by remember { mutableStateOf(coffee) }
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        RegularAppText(
            text = "Редактирование",
            fontSize = 24.sp,
            fontFamily = getComfortaBold()
        )
        Spacer(Modifier.height(10.dp))
        AppOutlinedTextField(
            text = editableCoffee.title,
            label = "Введи название",
            borderColor = lightGray,
            labelColor = textSecondaryColor,
            onTextChange = { newTitle ->
                editableCoffee = editableCoffee.copy(title = newTitle)
            }
        )
        AppOutlinedTextField(
            text = editableCoffee.qGrade ?: "",
            label = "Введи QGrade",
            borderColor = lightGray,
            labelColor = textSecondaryColor,
            onTextChange = { qGrade ->
                editableCoffee = editableCoffee.copy(qGrade = qGrade)
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
        Spacer(Modifier.height(10.dp))
        RegularAppText(
            text = "Плотность",
            fontSize = 20.sp,
            color = textSecondaryColor
        )
        AppSlider(
            value = coffee.density,
            onValueChangeFinished = {
                editableCoffee = editableCoffee.copy(density = it)
            }
        )
        RegularAppText(
            text = "Кислотность",
            fontSize = 20.sp,
            color = textSecondaryColor
        )
        AppSlider(
            value = coffee.acidity,
            onValueChangeFinished = {
                editableCoffee = editableCoffee.copy(acidity = it)
            }
        )
        AppOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            text = editableCoffee.userDescription ?: "",
            label = "Введи описание",
            borderColor = lightGray,
            labelColor = textSecondaryColor,
            onTextChange = { desc ->
                editableCoffee = editableCoffee.copy(userDescription = desc)
            }
        )
        Spacer(Modifier.height(5.dp))
        AppButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = {
                RegularAppText(
                    text = "Сохранить",
                    color = white
                )
            },
            contentPadding = PaddingValues(15.dp),
            icon = null,
            onClick = {
                onSaveClick(editableCoffee)
            }
        )
    }
}