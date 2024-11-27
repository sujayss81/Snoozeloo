package org.sujay.snoozeloo.core.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import snoozeloo.composeapp.generated.resources.Montserrat_Italic_VariableFont_wght
import snoozeloo.composeapp.generated.resources.Montserrat_VariableFont_wght
import snoozeloo.composeapp.generated.resources.Res

@Composable
fun MonteserratFontFamily() = FontFamily(
    Font(Res.font.Montserrat_VariableFont_wght, weight = FontWeight.Normal),
    Font(Res.font.Montserrat_Italic_VariableFont_wght, style = FontStyle.Italic)
)

@Composable
fun MonteserratTypography() = Typography(
    headlineLarge = TextStyle(
        fontFamily = MonteserratFontFamily(),
        fontSize = 24.sp,
        fontWeight = FontWeight.W500
    ),
    displayLarge = TextStyle(
        fontFamily = MonteserratFontFamily(),
        fontSize = 82.sp,
        fontWeight = FontWeight.W500
    ),
    bodyMedium = TextStyle(
        fontFamily = MonteserratFontFamily(),
        fontSize = 16.sp,
        fontWeight = FontWeight.W500
    ),
    bodySmall = TextStyle(
        fontFamily = MonteserratFontFamily(),
        fontSize = 14.sp,
        fontWeight = FontWeight.W500
    ),
)
