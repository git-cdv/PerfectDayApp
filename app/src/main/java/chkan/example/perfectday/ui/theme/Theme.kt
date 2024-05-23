package chkan.example.perfectday.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = MainDarkGreen, // Dark Green (заголовки, кнопки)
    secondary = SecondaryOliveGreen, // Olive Green (фоновая заливка элементов)
    background = BgAlmostBlack, // Almost Black (фон)
    surface = BgAlmostBlack, // Almost Black (для карточек, диалогов и т.д.)
    onPrimary = BgWhite, // White (текст/icons на primary цвете)
    onSecondary = BgWhite, // White (текст/icons на secondary цвете)
    onBackground = TextLiteGreen, // Light Gray (текст на фоне)
    onSurface = TextLiteGreen, // Light Gray (текст на surface)
    tertiary = AccentLavender, // Lavender (иконки, ссылки)
)

private val LightColorScheme = lightColorScheme(
    primary = MainDarkGreen, // Dark Green (заголовки, кнопки)
    secondary = SecondaryLightGreen, // Light Green (фоновая заливка элементов)
    background = BgWhite, // White (фон)
    surface = BgWhite, // White (для карточек, диалогов и т.д.)
    onPrimary = BgWhite, // White (текст/icons на primary цвете)
    onSecondary = BgAlmostBlack, // Black (текст/icons на secondary цвете)
    onBackground = TextDarkGreen, // Dark Gray (текст на фоне)
    onSurface = TextDarkGreen, // Dark Gray (текст на surface)
    tertiary = AccentPeach //(иконки, ссылки)

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
fun PerfectDayTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.secondary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}