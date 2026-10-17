
package com.example.calculocm.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = MyBlue,
    onPrimary = MyWhite,

    secondary = MyDarkBlue,
    onSecondary = MyWhite,

    tertiary = MyLightBlue,
    onTertiary = MyDarkBlue,

    background = MyBackground,
    onBackground = MyDarkBlue,

    surface = MyWhite,
    onSurface = MyDarkBlue,

    outline = MyBorder,
    error = MyError,
    onError = MyWhite
)

@Composable
fun CalculoCMTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
