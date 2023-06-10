package app.doggy.mybrary.core.domain.repository

import app.doggy.mybrary.core.domain.model.book.Book
import app.doggy.mybrary.core.domain.model.book.BookId
import app.doggy.mybrary.core.domain.model.record.Record
import kotlinx.coroutines.flow.Flow

interface BookRepository {

  suspend fun registerBook(book: Book)

  suspend fun updateBook(book: Book)

  suspend fun deleteBook(bookId: BookId)

  fun getBooks(): Flow<List<Book>>

  fun getBook(bookId: BookId): Flow<Book>

  fun getBookWithRecords(bookId: BookId): Flow<Map<Book, List<Record>>>

  fun searchBooksByIsbn(
    isbn: String,
    limit: Int,
    pageIndex: Int,
  ): Flow<List<Book>>
}
