package com.example.uthsmarttasks.ui.theme

import android.app.Activity
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

// Light Color Scheme tùy chỉnh
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1976D2), // Màu xanh dương cho "UTH SmartTasks"
    onPrimary = Color.White,
    secondary = Color(0xFFDC004E), // Màu đỏ cho "University of Transport Ho Chi Minh City"
    onSecondary = Color.White,
    background = Color.White, // Nền trắng cho Splash Screen
    surface = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

// Dark Color Scheme (dùng khi người dùng bật chế độ tối)
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

@Composable
fun UTHSmartTasksTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Mặc định theo hệ thống, nhưng ưu tiên Light
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
        else -> LightColorScheme // Mặc định là Light Theme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}