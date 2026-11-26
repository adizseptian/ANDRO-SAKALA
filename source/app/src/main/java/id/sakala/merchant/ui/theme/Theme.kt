package id.sakala.merchant.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val SakalaColors = lightColorScheme(
    primary = Evergreen,
    onPrimary = SurfaceWhite,
    secondary = Champagne,
    background = Porcelain,
    onBackground = Ink,
    surface = SurfaceWhite,
    onSurface = Ink,
    outline = Line,
    error = Error
)

@Composable
fun SakalaTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = SakalaColors, content = content)
}
