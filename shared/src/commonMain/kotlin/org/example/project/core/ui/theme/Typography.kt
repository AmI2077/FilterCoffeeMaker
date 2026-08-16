package org.example.project.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import coffee.shared.generated.resources.Montserrat_Bold
import coffee.shared.generated.resources.Montserrat_ExtraBold
import coffee.shared.generated.resources.Montserrat_Medium
import coffee.shared.generated.resources.Montserrat_Regular
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.comfortaa_bold
import coffee.shared.generated.resources.comfortaa_regular
import org.jetbrains.compose.resources.Font

private val montserratRegular = Res.font.Montserrat_Regular
private val montserratBold = Res.font.Montserrat_Bold
private val montserratExtraBold = Res.font.Montserrat_ExtraBold
private val montserratMedium = Res.font.Montserrat_Medium

@Composable
fun getMontserratRegular(): FontFamily {
    return FontFamily(
        Font(montserratRegular)
    )
}

@Composable
fun getMontserratMedium(): FontFamily {
    return FontFamily(
        Font(montserratMedium)
    )
}

@Composable
fun getMontserratBold(): FontFamily {
    return FontFamily(
        Font(montserratBold)
    )
}

@Composable
fun getMontserratExtraBold(): FontFamily {
    return FontFamily(
        Font(montserratExtraBold)
    )
}