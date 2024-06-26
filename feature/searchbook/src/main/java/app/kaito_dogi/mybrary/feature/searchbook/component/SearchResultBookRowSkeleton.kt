package app.kaito_dogi.mybrary.feature.searchbook.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import app.kaito_dogi.mybrary.core.designsystem.theme.MybraryTheme
import app.kaito_dogi.mybrary.core.ui.component.SkeletonBox

@Composable
fun SearchResultBookRowSkeleton(
  modifier: Modifier = Modifier,
) {
  SkeletonBox(
    modifier = modifier
      .clip(shape = MybraryTheme.shapes.small)
      .fillMaxWidth(),
  ) {
    Row(
      modifier = Modifier
        .padding(MybraryTheme.space.md)
        .height(IntrinsicSize.Min)
        .fillMaxWidth(),
    ) {
      Column(
        verticalArrangement = Arrangement.spacedBy(MybraryTheme.space.xxs),
      ) {
        Text(
          text = "あ\nあ",
          modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
          color = Color.Transparent,
          overflow = TextOverflow.Ellipsis,
          maxLines = 2,
          style = MybraryTheme.typography.titleMedium,
        )
        Text(
          text = "あ",
          modifier = Modifier.fillMaxWidth(),
          color = Color.Transparent,
          overflow = TextOverflow.Ellipsis,
          maxLines = 1,
          style = MybraryTheme.typography.bodyMedium,
        )
        Text(
          text = "あ",
          modifier = Modifier.fillMaxWidth(),
          color = Color.Transparent,
          overflow = TextOverflow.Ellipsis,
          maxLines = 1,
          style = MybraryTheme.typography.bodyMedium,
        )
      }
    }
  }
}

@Preview
@Composable
private fun SearchResultBookRowSkeletonPreview() {
  MybraryTheme {
    SearchResultBookRowSkeleton()
  }
}
