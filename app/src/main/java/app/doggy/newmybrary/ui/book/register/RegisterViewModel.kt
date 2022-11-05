package app.doggy.newmybrary.ui.book.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.doggy.newmybrary.domain.model.Book
import app.doggy.newmybrary.domain.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
  private val bookRepository: BookRepository,
) : ViewModel() {
  private val _uiState = MutableStateFlow(RegisterState.initial())
  val uiState: StateFlow<RegisterState> = _uiState.asStateFlow()

  fun onRegisterButtonClick(
    title: String,
    author: String,
    description: String,
    totalPage: String,
  ) {
    viewModelScope.launch {
      _uiState.update { it.copy(isLoading = true) }
      runCatching {
        val isValid = title.isNotBlank() && author.isNotBlank() && totalPage.isNotBlank() && totalPage.first() != '0'
        if (!isValid) throw IllegalArgumentException()

        bookRepository.registerBook(
          book = Book(
            id = 0L,
            booksApiId = null,
            title = title,
            authors = listOf(
              author,
            ),
            description = description,
            totalPage = totalPage.toInt(),
            imageUrl = null,
            diaryList = listOf(),
            registeredAt = Date(),
          ),
        )
      }.onSuccess {
        _uiState.update { it.copy(isLoading = false) }
        // TODO: 成功した時のイベントを発火する
      }.onFailure {
        _uiState.update { it.copy(isLoading = false) }
        // TODO: エラーハンドリング
      }
    }
  }
}
