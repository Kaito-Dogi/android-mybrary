package app.kaito_dogi.mybrary.core.supabase.datasource

import app.kaito_dogi.mybrary.core.common.coroutines.MybraryDispatcher
import app.kaito_dogi.mybrary.core.common.coroutines.MybraryDispatchers
import app.kaito_dogi.mybrary.core.data.command.PostBookCommand
import app.kaito_dogi.mybrary.core.data.datasource.BookRemoteDataSource
import app.kaito_dogi.mybrary.core.data.dto.BookDto
import app.kaito_dogi.mybrary.core.supabase.input.toBookInput
import app.kaito_dogi.mybrary.core.supabase.response.BookResponse
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

private const val BOOK_TABLE = "book"

internal class DefaultBookRemoteDataSource @Inject constructor(
  private val supabaseClient: SupabaseClient,
  @MybraryDispatcher(MybraryDispatchers.Io) private val dispatcher: CoroutineDispatcher,
) : BookRemoteDataSource {
  override suspend fun getBookByIsbn(isbn: String): BookDto? = withContext(dispatcher) {
    val result = supabaseClient.postgrest
      .from(BOOK_TABLE)
      .select(
        columns = Columns.ALL,
        request = {
          filter {
            BookResponse::isbn eq isbn
          }
        },
      )
    val response = result.decodeSingleOrNull<BookResponse>()
    return@withContext response?.toBookDto()
  }

  override suspend fun postBook(command: PostBookCommand): BookDto = withContext(dispatcher) {
    val input = command.toBookInput()
    val result = supabaseClient.postgrest
      .from(BOOK_TABLE)
      .insert(
        value = input,
        request = {
          select(columns = Columns.ALL)
        },
      )
    val response = result.decodeSingle<BookResponse>()
    return@withContext response.toBookDto()
  }
}
