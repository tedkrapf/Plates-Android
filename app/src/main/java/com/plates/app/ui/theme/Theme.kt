package com.plates.app.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColors = darkColorScheme(
    primary = SignYellow,
    onPrimary = AsphaltGray,
    primaryContainer = RoadBlue,
    onPrimaryContainer = SkySurface,
    secondary = RoadGreen,
    background = NightSurface,
    surface = NightSurface
)

private val LightColors = lightColorScheme(
    primary = RoadBlue,
    onPrimary = SkySurface,
    primaryContainer = SkySurface,
    onPrimaryContainer = RoadBlue,
    secondary = RoadGreen,
    background = SkySurface,
    surface = SkySurface
)

@Composable
fun PlatesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
