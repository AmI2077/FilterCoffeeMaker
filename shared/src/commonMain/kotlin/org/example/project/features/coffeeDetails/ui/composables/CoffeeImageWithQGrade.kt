package org.example.project.features.coffeeDetails.ui.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.ic_coffee_image_placeholder
import org.example.project.core.ui.theme.UiDefaults
import org.example.project.core.ui.theme.blueGrayText
import org.example.project.features.addCoffee.ui.composables.CoffeeImage
import org.jetbrains.compose.resources.painterResource

@Composable
fun CoffeeImageWithQGrade(
    modifier: Modifier = Modifier,
    model: Any?,
    qGrade: String?,
) {
    Box {
        CoffeeImage(
            modifier = modifier
                .border(
                    width = 2.dp,
                    color = blueGrayText,
                    shape = RoundedCornerShape(UiDefaults.IMAGE_CORNERS_RADIUS.dp)
                )
                .aspectRatio(UiDefaults.IMAGE_ASPECT_RATIO)
                .fillMaxWidth(),
            placeholder = painterResource(Res.drawable.ic_coffee_image_placeholder),
            model = model
        )
        qGrade?.let { grade ->
            QGradeBox(
                modifier = Modifier
                    .padding(15.dp),
                qGrade = grade
            )
        }
    }
}