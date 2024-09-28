package com.example.dicodingevent.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Warna untuk tema gelap
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    onPrimary = Color.White,
    secondary = PurpleGrey80,
    onSecondary = Color.White,
    tertiary = Pink80,
    onTertiary = Color.White,
    background = Color(0xFF121212), // Background warna gelap
    surface = Color(0xFF121212), // Surface warna gelap
    onBackground = Color.White, // Teks dan elemen lain agar tetap terbaca
    onSurface = Color.White
)

// Warna untuk tema terang
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    onPrimary = Color.White,
    secondary = PurpleGrey40,
    onSecondary = Color.Black,
    tertiary = Pink40,
    onTertiary = Color.Black,
    background = Color(0xFFFFFBFE), // Background warna terang
    surface = Color(0xFFFFFBFE), // Surface warna terang
    onBackground = Color.Black, // Teks dan elemen lain agar tetap terbaca
    onSurface = Color.Black
)

@Composable
fun DicodingEventTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
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
