package app.kaito_dogi.mybrary.feature.login.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.kaito_dogi.mybrary.core.designsystem.theme.MybraryTheme

@Composable
internal fun DividerSection(
  modifier: Modifier = Modifier,
) {
  Row(
    modifier = modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(MybraryTheme.space.xs),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    HorizontalDivider(modifier = Modifier.weight(1f))
    Text(
      text = "または",
      color = MybraryTheme.colorScheme.outlineVariant,
      style = MybraryTheme.typography.labelMedium,
    )
    HorizontalDivider(modifier = Modifier.weight(1f))
  }
}

@Preview
@Composable
private fun DividerSectionPreview() {
  MybraryTheme {
    DividerSection()
  }
}
