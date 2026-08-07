package org.example.project.features.addCoffee.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.core.ui.components.RegularAppText
import org.example.project.ui.components.CustomProgressIndicator

@Composable
fun CoffeeBalance(
    modifier: Modifier = Modifier,
    density: Float,
    acidity: Float,
) {
    Column(
        modifier = modifier,
    ) {
        RegularAppText(
            text = "Плотность",
            fontSize = 18.sp,
        )
        CustomProgressIndicator(
            modifier = Modifier
                .padding(top = 5.dp)
                .height(10.dp),
            progress = density
        )
        RegularAppText(
            modifier = Modifier
                .padding(top = 20.dp),
            text = "Кислотность",
            fontSize = 18.sp,
        )
        CustomProgressIndicator(
            modifier = Modifier
                .padding(top = 5.dp)
                .height(10.dp),
            progress = acidity
        )
    }
}