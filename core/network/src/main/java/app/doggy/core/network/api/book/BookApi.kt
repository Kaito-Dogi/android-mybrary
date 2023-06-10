package app.doggy.core.network.api.book

import app.doggy.mybrary.core.domain.model.book.Book

interface BookApi {

  suspend fun searchBooksByIsbn(
    isbn: String,
    limit: Int,
    pageIndex: Int,
  ): List<Book>
}
