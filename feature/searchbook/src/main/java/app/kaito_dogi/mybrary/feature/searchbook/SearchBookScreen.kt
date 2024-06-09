package app.kaito_dogi.mybrary.feature.searchbook

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun SearchBookScreen(
  viewModel: SearchBookViewModel = hiltViewModel(),
) {
  SearchBookPage()
}
