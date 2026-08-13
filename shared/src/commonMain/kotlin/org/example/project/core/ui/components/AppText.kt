package org.example.project.core.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import org.example.project.core.ui.theme.black
import org.example.project.core.ui.theme.getCaveatBold
import org.example.project.core.ui.theme.getComfortaRegular
import org.example.project.core.ui.theme.headerTextStyle
import org.example.project.core.ui.theme.regularTextStyle

@Composable
fun RegularAppText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = black,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign? = null,
    fontSize: TextUnit = 20.sp,
    fontFamily: FontFamily = getComfortaRegular(),
    lineHeight: TextUnit = TextUnit.Unspecified
) {
    Text(
        modifier = modifier,
        text = text,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        lineHeight = lineHeight,
        style = regularTextStyle.copy(
            fontFamily = fontFamily,
            color = color,
            fontSize = fontSize
        )
    )
}

@Composable
fun HeaderAppText(
    modifier: Modifier = Modifier,
    text: String,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    color: Color = black,
    fontSize: TextUnit = 42.sp,
    fontFamily: FontFamily = getCaveatBold()
) {
    Text(
        modifier = modifier,
        text = text,
        maxLines = maxLines,
        overflow = overflow,
        style = headerTextStyle.copy(
            color = color,
            fontSize = fontSize,
            fontFamily = fontFamily
        )
    )
}