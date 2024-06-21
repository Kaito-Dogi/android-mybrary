package app.kaito_dogi.mybrary.feature.mybookdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.kaito_dogi.mybrary.core.domain.model.DraftMemo
import app.kaito_dogi.mybrary.core.domain.model.Memo
import app.kaito_dogi.mybrary.core.domain.repository.MemoRepository
import app.kaito_dogi.mybrary.core.domain.repository.MyBookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
internal class MyBookDetailViewModel @Inject constructor(
  private val myBookRepository: MyBookRepository,
  private val memoRepository: MemoRepository,
  savedStateHandle: SavedStateHandle,
) : ViewModel() {
  private val navArg: MyBookDetailNavArg = checkNotNull(savedStateHandle[myBookDetailNavArgName])

  private val _uiState = MutableStateFlow(
    MyBookDetailUiState.createInitialValue(
      myBook = navArg.myBook,
    ),
  )
  val uiState = _uiState.asStateFlow()

  fun init() {
    viewModelScope.launch {
      try {
        val memoList = memoRepository.getMemos(navArg.myBook.id)
        _uiState.update { it.copy(memoList = memoList) }
      } catch (e: Exception) {
        // TODO: デバッグ用のログを実装する
        println("あああ: ${e.message}")
      }
    }
  }

  fun onArchiveClick() {
    viewModelScope.launch {
      try {
        myBookRepository.archiveBook(navArg.myBook.id)
      } catch (e: Exception) {
        // TODO: デバッグ用のログを実装する
        println("あああ: ${e.message}")
      }
    }
  }

  fun onFavoriteClick() {
    viewModelScope.launch {
      try {
        val myBook = myBookRepository.makeBookFavorite(navArg.myBook.id)
        _uiState.update { it.copy(myBook = myBook) }
      } catch (e: Exception) {
        // TODO: デバッグ用のログを実装する
        println("あああ: ${e.message}")
      }
    }
  }

  fun onEditClick() {
    _uiState.update { it.copy(isBottomSheetVisible = true) }
  }

  fun onMemoClick(memo: Memo) {
    _uiState.update {
      it.copy(
        isBottomSheetVisible = true,
        editedMemoId = memo.id,
        draftMemo = it.draftMemo.copy(
          content = memo.content,
          fromPage = memo.fromPage,
          toPage = memo.toPage,
        ),
      )
    }
  }

  fun onBottomSheetDismissRequest() {
    _uiState.update {
      it.copy(
        isBottomSheetVisible = false,
        editedMemoId = null,
        draftMemo = if (it.editedMemoId == null) {
          it.draftMemo
        } else {
          DraftMemo.createInitialValue(
            navArg.myBook.id,
          )
        },
      )
    }
  }

  fun onFromPageChange(fromPage: String) {
    _uiState.update {
      it.copy(
        draftMemo = it.draftMemo.copy(
          fromPage = if (fromPage.isNotBlank()) fromPage.toInt() else null,
        ),
      )
    }
  }

  fun onToPageChange(toPage: String) {
    _uiState.update {
      it.copy(
        draftMemo = it.draftMemo.copy(
          toPage = if (toPage.isNotBlank()) toPage.toInt() else null,
        ),
      )
    }
  }

  fun onContentChange(content: String) {
    _uiState.update {
      it.copy(
        draftMemo = it.draftMemo.copy(
          content = content,
        ),
      )
    }
  }

  fun onSaveClick() {
    viewModelScope.launch {
      try {
        val memoId = uiState.value.editedMemoId
        if (memoId == null) {
          memoRepository.createMemo(
            draftMemo = uiState.value.draftMemo,
          )
        } else {
          memoRepository.editMemo(
            memoId = memoId,
            draftMemo = uiState.value.draftMemo,
          )
        }
        _uiState.update {
          it.copy(
            editedMemoId = null,
            draftMemo = DraftMemo.createInitialValue(navArg.myBook.id),
          )
        }
      } catch (e: Exception) {
        // TODO: デバッグ用のログを実装する
        println("あああ: ${e.message}")
      }
    }
  }
}
