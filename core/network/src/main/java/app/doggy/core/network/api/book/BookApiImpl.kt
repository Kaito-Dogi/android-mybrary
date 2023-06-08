package app.doggy.core.network.api.book

import app.doggy.core.common.exception.NetworkException
import app.doggy.core.network.retrofit.service.BookService
import app.doggy.mybrary.core.domain.model.book.Book
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class BookApiImpl @Inject constructor(
  private val bookService: BookService,
) : BookApi {

  override suspend fun searchBooksByIsbn(
    isbn: String,
    limit: Int,
    pageIndex: Int,
  ): Result<List<Book>> {
    val response = bookService.getVolumes(
      q = isbn,
      maxResult = limit,
      startIndex = pageIndex,
    )

    return if (response.isSuccessful) {
      Result.success(
        response.body()?.items?.map { it.toBook() } ?: listOf(),
      )
    } else {
      Result.failure(
        NetworkException(
          code = response.code(),
          errorBody = response.errorBody().toString(),
        ),
      )
    }
  }
}
