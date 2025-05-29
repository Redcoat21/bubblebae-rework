package com.dahrenericsson.bubblebaerework.presentation.theme

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dahrenericsson.bubblebaerework.R

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun BubbleBaeReworkTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun BubbleBaeBackground(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Background blur layer (optional, can be removed if not needed)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(80.dp)
                .background(
                    Color.White.copy(alpha = 0.8f)
                )
        )

        // Large pink bubble in top-left
        Image(
            painter = painterResource(id = R.drawable.bubble_pink), // Your bubble PNG
            contentDescription = "Bubble decoration",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(500.dp)  // Large size to ensure visibility
                .offset(x = (-150).dp, y = (-100).dp)  // Position partly off-screen
                .alpha(0.7f)   // Semi-transparent
        )

//         Large blue bubble in bottom-right
        Image(
            painter = painterResource(id = R.drawable.bubble_blue), // Same or different bubble PNG
            contentDescription = "Bubble decoration",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(350.dp)
                .offset(x = 180.dp, y = 70.dp)  // Position partly off-screen at bottom-right
                .alpha(0.6f)
        )
        // Optional: Small bubble in middle-right
        Image(
            painter = painterResource(id = R.drawable.bubble_orange), // Same or different bubble PNG
            contentDescription = "Bubble decoration",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(400.dp)
                .offset(x = (-120).dp, y = 550.dp)
                .alpha(0.5f)
        )

        Image(
            painter = painterResource(id = R.drawable.bubble_purple), // Same or different bubble PNG
            contentDescription = "Bubble decoration",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(350.dp)
                .offset(x = 180.dp, y = 400.dp)
                .alpha(0.5f)
        )
        Box(modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}