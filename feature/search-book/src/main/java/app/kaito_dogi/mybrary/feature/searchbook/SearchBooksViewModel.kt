package app.kaito_dogi.mybrary.feature.searchbook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.kaito_dogi.mybrary.core.domain.model.Book
import app.kaito_dogi.mybrary.core.domain.repository.MyBookRepository
import app.kaito_dogi.mybrary.core.domain.repository.SearchBooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
internal class SearchBooksViewModel @Inject constructor(
  private val searchBooksRepository: SearchBooksRepository,
  private val myBookRepository: MyBookRepository,
) : ViewModel() {
  private val _uiState = MutableStateFlow(SearchBookUiState.InitialValue)
  val uiState = _uiState.asStateFlow()

  fun onSearchQueryChange(searchQuery: String) {
    viewModelScope.launch {
      _uiState.update {
        it.copy(
          bookList = null,
          searchQuery = searchQuery,
          isSearching = true,
        )
      }
      if (searchQuery.isNotBlank()) {
        try {
          val searchResult = searchBooksRepository.searchBooks(
            query = searchQuery,
            startIndex = 0,
          )
          _uiState.update { it.copy(bookList = searchResult) }
        } catch (e: Exception) {
          // TODO: 共通のエラーハンドリングを表示
          println("あああ: $e")
        }
      }
    }.invokeOnCompletion {
      _uiState.update { it.copy(isSearching = false) }
    }
  }

  fun onBookLongClick(book: Book) {
    _uiState.update {
      it.copy(
        selectedBook = book,
        isDialogShown = true,
      )
    }
  }

  fun onConfirmClick() {
    viewModelScope.launch {
      uiState.value.selectedBook?.let { selectedBook ->
        try {
          _uiState.update { it.copy(isBookRegistering = true) }

          myBookRepository.registerMyBook(book = selectedBook)

          _uiState.update {
            // FIXME: String Resources を渡すようにする
            it.copy(
              shownMessage = "『${selectedBook.title}』をMybraryに追加しました",
              isDialogShown = false,
            )
          }
        } catch (e: Exception) {
          // TODO: 共通のエラーハンドリングを表示
          println("あああ: $e")
        } finally {
          _uiState.update { it.copy(isBookRegistering = false) }
        }
      }
    }
  }

  fun onDismissClick() {
    _uiState.update { it.copy(isDialogShown = false) }
  }

  fun onMessageShown() {
    _uiState.update {
      it.copy(
        shownMessage = null,
      )
    }
  }
}
