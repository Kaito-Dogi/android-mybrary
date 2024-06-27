package app.kaito_dogi.mybrary.feature.searchbook

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.kaito_dogi.mybrary.core.designsystem.theme.MybraryTheme
import app.kaito_dogi.mybrary.core.domain.model.SearchResultBook
import app.kaito_dogi.mybrary.feature.searchbook.component.SearchBookBottomAppBar
import app.kaito_dogi.mybrary.feature.searchbook.component.SearchResultBookRow
import app.kaito_dogi.mybrary.feature.searchbook.component.SearchResultBookRowSkeleton

@Composable
internal fun SearchBookScreen(
  uiState: SearchBookUiState,
  snackbarHost: @Composable () -> Unit,
  showSnackbar: (String) -> Unit,
  onSearchQueryChange: (String) -> Unit,
  onBarcodeScannerClick: () -> Unit,
  onSearchResultClick: (SearchResultBook) -> Unit,
  onSearchResultLongClick: (SearchResultBook) -> Unit,
) {
  Scaffold(
    bottomBar = {
      SearchBookBottomAppBar(
        value = uiState.searchQuery,
        onValueChange = onSearchQueryChange,
        onBarcodeScannerClick = onBarcodeScannerClick,
        modifier = Modifier.windowInsetsPadding(WindowInsets.ime),
      )
    },
    snackbarHost = snackbarHost,
  ) { innerPadding ->
    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      contentPadding = PaddingValues(
        start = MybraryTheme.space.md,
        top = MybraryTheme.space.md + innerPadding.calculateTopPadding(),
        end = MybraryTheme.space.md,
        bottom = MybraryTheme.space.md + innerPadding.calculateBottomPadding(),
      ),
      verticalArrangement = Arrangement.spacedBy(MybraryTheme.space.md),
    ) {
      uiState.searchResults?.let {
        items(
          items = uiState.searchResults,
          key = { it.externalId.value },
        ) {
          SearchResultBookRow(
            searchResultBook = it,
            onClick = onSearchResultClick,
            onLongClick = onSearchResultLongClick,
          )
        }
      }

      if (uiState.isSearching) {
        items(
          count = 4,
          key = { "SearchResultBookRowSkeleton$it" },
        ) {
          SearchResultBookRowSkeleton()
        }
      }
    }

    uiState.shownMessage?.let {
      LaunchedEffect(it) {
        showSnackbar(it)
      }
    }
  }
}

@Preview
@Composable
private fun SearchBookScreenPreview() {
  MybraryTheme {
    SearchBookScreen(
      uiState = SearchBookUiState.InitialValue,
      snackbarHost = {},
      showSnackbar = {},
      onSearchQueryChange = {},
      onBarcodeScannerClick = {},
      onSearchResultClick = {},
      onSearchResultLongClick = {},
    )
  }
}
