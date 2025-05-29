package com.dahrenericsson.bubblebaerework.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.dahrenericsson.bubblebaerework.R

val BriceFontFamily = FontFamily(
    Font(R.font.brice_regular, FontWeight.Normal),
    Font(R.font.brice_black, FontWeight.Bold)
)

val CyGroteskFamily = FontFamily(
    Font(R.font.cy_grotesk_regular, FontWeight.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = BriceFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 50.sp,
        textAlign = TextAlign.Center,
        letterSpacing = 0.7.sp
    ),
    titleSmall = TextStyle(
        fontFamily = BriceFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        letterSpacing = 0.4.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = CyGroteskFamily,
        fontSize = 13.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)