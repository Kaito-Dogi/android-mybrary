package app.kaito_dogi.mybrary.core.designsystem.component

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import app.kaito_dogi.mybrary.core.designsystem.theme.MybraryTheme

@Composable
fun SkeletonBox(
  shape: Shape,
  modifier: Modifier = Modifier,
  content: @Composable BoxScope.() -> Unit,
) {
  val infiniteTransition = rememberInfiniteTransition(label = "スケルトン表示")
  val alpha by infiniteTransition.animateFloat(
    initialValue = 1.0f,
    targetValue = 0.2f,
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 1_000),
      repeatMode = RepeatMode.Reverse,
    ),
    label = "スケルトン表示",
  )

  Box(
    modifier = modifier
      .clip(shape = shape)
      .background(MybraryTheme.colorScheme.surfaceVariant.copy(alpha = alpha)),
    content = content,
  )
}

@Preview
@Composable
private fun SkeletonBoxPreview() {
  MybraryTheme {
    SkeletonBox(
      shape = MybraryTheme.shapes.small,
    ) {
      Box(modifier = Modifier.size(MybraryTheme.spaces.xxxl))
    }
  }
}
