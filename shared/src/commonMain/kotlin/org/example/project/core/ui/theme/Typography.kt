package org.example.project.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.caveat_bold
import coffee.shared.generated.resources.caveat_regular
import coffee.shared.generated.resources.comfortaa_bold
import coffee.shared.generated.resources.comfortaa_regular
import coffee.shared.generated.resources.ptsans_bold
import coffee.shared.generated.resources.ptsans_regular
import org.jetbrains.compose.resources.Font


private val ptSansRegular = Res.font.ptsans_regular
private val ptSansBold = Res.font.ptsans_bold
private val comfortaRegular = Res.font.comfortaa_regular
private val comfortaBold = Res.font.comfortaa_bold
private val caveatRegular = Res.font.caveat_regular
private val caveatBold = Res.font.caveat_bold

@Composable
fun getPtSansRegular(): FontFamily {
    return FontFamily(
        Font(ptSansRegular)
    )
}

@Composable
fun getPtSansBold(): FontFamily {
    return FontFamily(
        Font(Res.font.ptsans_bold)
    )
}

@Composable
fun getComfortaRegular(): FontFamily {
    return FontFamily(
        Font(comfortaRegular)
    )
}

@Composable
fun getComfortaBold(): FontFamily {
    return FontFamily(
        Font(comfortaBold)
    )
}

@Composable
fun getCaveatBold(): FontFamily {
    return FontFamily(
        Font(caveatBold)
    )
}