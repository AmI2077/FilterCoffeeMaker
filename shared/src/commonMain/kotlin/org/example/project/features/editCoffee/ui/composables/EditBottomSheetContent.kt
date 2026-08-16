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
import org.example.project.core.ui.theme.getMontserratBold
import org.example.project.core.ui.theme.white
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.acidity_label
import coffee.shared.generated.resources.density_label
import coffee.shared.generated.resources.edit_title
import coffee.shared.generated.resources.enter_description_label
import coffee.shared.generated.resources.enter_name_label
import coffee.shared.generated.resources.enter_qgrade_label
import coffee.shared.generated.resources.processing_label
import coffee.shared.generated.resources.roasting_label
import coffee.shared.generated.resources.save_button
import org.jetbrains.compose.resources.stringResource

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
            text = stringResource(Res.string.edit_title),
            fontSize = 24.sp,
            fontFamily = getMontserratBold()
        )
        Spacer(Modifier.height(10.dp))
        AppOutlinedTextField(
            text = editableCoffee.title,
            label = stringResource(Res.string.enter_name_label),
            borderColor = lightGray,
            labelColor = textSecondaryColor,
            onTextChange = { newTitle ->
                editableCoffee = editableCoffee.copy(title = newTitle)
            }
        )
        AppOutlinedTextField(
            text = editableCoffee.qGrade ?: "",
            label = stringResource(Res.string.enter_qgrade_label),
            borderColor = lightGray,
            labelColor = textSecondaryColor,
            onTextChange = { qGrade ->
                editableCoffee = editableCoffee.copy(qGrade = qGrade)
            }
        )
        AppDropdownMenu(
            value = editableCoffee.roasting,
            values = roastingTypes,
            label = stringResource(Res.string.roasting_label)
        )
        AppDropdownMenu(
            value = editableCoffee.processingMethod,
            values = processingMethods,
            label = stringResource(Res.string.processing_label)
        )
        Spacer(Modifier.height(10.dp))
        RegularAppText(
            text = stringResource(Res.string.density_label),
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
            text = stringResource(Res.string.acidity_label),
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
            label = stringResource(Res.string.enter_description_label),
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
                    text = stringResource(Res.string.save_button),
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