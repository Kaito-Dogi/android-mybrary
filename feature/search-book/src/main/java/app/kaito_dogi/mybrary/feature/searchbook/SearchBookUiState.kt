package app.kaito_dogi.mybrary.feature.searchbook

import androidx.compose.runtime.Immutable
import app.kaito_dogi.mybrary.core.domain.model.Book

@Immutable
internal data class SearchBookUiState(
  val bookList: List<Book>?,
  val searchQuery: String,
  val isSearching: Boolean,
  val selectedBook: Book?,
  val isDialogShown: Boolean,
  val isBookRegistering: Boolean,
  val shownMessage: String?,
) {
  companion object {
    val InitialValue = SearchBookUiState(
      bookList = null,
      searchQuery = "",
      isSearching = false,
      selectedBook = null,
      isDialogShown = false,
      isBookRegistering = false,
      shownMessage = null,
    )
  }
}
