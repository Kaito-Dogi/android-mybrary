package app.doggy.newmybrary.ui.home

import app.doggy.newmybrary.domain.model.Book

sealed interface HomeUiModel {
  class BookItem(
    val book: Book,
  ) : HomeUiModel
}
