package app.kaito_dogi.mybrary.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Brush

@Composable
fun MybraryTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) {
    mybraryDarkColorScheme
  } else {
    mybraryLightColorScheme
  }

  CompositionLocalProvider(
    LocalDimens provides MybraryTheme.dimens,
    LocalShapes provides MybraryTheme.shapes,
    LocalSpaces provides MybraryTheme.spaces,
  ) {
    MaterialTheme(
      // FIXME: カラースキーマをカスタムで定義する
      colorScheme = colorScheme,
      typography = mybraryTypography,
      content = content,
    )
  }
}

object MybraryTheme {
  val colorScheme: ColorScheme
    @Composable
    @ReadOnlyComposable
    get() = MaterialTheme.colorScheme

  val dimens
    @Composable
    @ReadOnlyComposable
    get() = LocalDimens.current

  val shapes: Shapes
    @Composable
    @ReadOnlyComposable
    get() = LocalShapes.current

  val spaces
    @Composable
    @ReadOnlyComposable
    get() = LocalSpaces.current

  val typography: Typography
    @Composable
    @ReadOnlyComposable
    get() = MaterialTheme.typography

  val backgroundBrush: Brush
    @Composable
    @ReadOnlyComposable
    get() = backgroundBrush()
}
