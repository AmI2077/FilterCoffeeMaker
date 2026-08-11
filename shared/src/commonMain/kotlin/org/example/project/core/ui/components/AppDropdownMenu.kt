package org.example.project.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.core.domain.model.roastingTypes
import org.example.project.core.ui.theme.blueGrayText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDropdownMenu(
    modifier: Modifier = Modifier,
    value: String = "Светлая",
    label: String,
    borderColor: Color = blueGrayText,
    labelColor: Color = blueGrayText,
    onValueChange: (String) -> Unit = {},
    values: List<String> = roastingTypes
) {
    var expanded by remember { mutableStateOf(false) }
    var fieldValue by remember(value) { mutableStateOf(value) }

    ExposedDropdownMenuBox(
        modifier = modifier
            .fillMaxWidth(),
        expanded = expanded,
        onExpandedChange = {
            expanded = it
        }
    ) {
        AppOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(
                    type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                    enabled = true
                ),
            text = fieldValue,
            borderColor = borderColor,
            labelColor = labelColor,
            label = label,
            onTextChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            content = {
                values.forEach { roastingType ->
                    DropdownMenuItem(
                        text = {
                            RegularAppText(
                                text = roastingType
                            )
                        },
                        onClick = {
                            fieldValue = roastingType
                            onValueChange(roastingType)
                            expanded = false
                        }
                    )
                }
            }
        )
    }
}