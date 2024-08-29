package app.kaito_dogi.mybrary.core.data.repository

import app.kaito_dogi.mybrary.core.api.rakuten.RakutenApi
import app.kaito_dogi.mybrary.core.domain.model.Book
import app.kaito_dogi.mybrary.core.domain.model.BookId
import app.kaito_dogi.mybrary.core.domain.model.Genre
import app.kaito_dogi.mybrary.core.domain.model.Sort
import app.kaito_dogi.mybrary.core.domain.repository.BookRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class BookRepositoryImpl @Inject constructor(
  private val rakutenApi: RakutenApi,
) : BookRepository {
  override suspend fun getBook(id: BookId): Book {
    TODO("Not yet implemented")
  }

  override suspend fun searchBook(
    title: String,
    isbn: String,
    genre: Genre,
    hits: Int,
    page: Int,
    sort: Sort,
  ): List<Book> {
    // TODO: MybraryConfig から楽天 API を叩くのに必要な ID を引数に渡す
    TODO("Not yet implemented")
  }
}
