package org.example.project.features.coffeeList.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.core.ui.theme.black
import org.example.project.core.ui.theme.getComfortaRegular
import org.example.project.core.ui.theme.regularTextStyle
import org.example.project.core.ui.theme.white

@Composable
private fun QGradeBox(qGrade: String) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .shadow(5.dp, RoundedCornerShape(100))
            .background(white, shape = RoundedCornerShape(100))

    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = qGrade,
            style = regularTextStyle.copy(
                fontSize = 14.sp,
                fontFamily = getComfortaRegular(),
                color = black
            )
        )
    }
}