package app.kaito_dogi.mybrary.core.supabase.datasource

import app.kaito_dogi.mybrary.core.common.coroutines.AppDispatcher
import app.kaito_dogi.mybrary.core.common.coroutines.AppDispatchers
import app.kaito_dogi.mybrary.core.data.command.PostMyBookCommand
import app.kaito_dogi.mybrary.core.data.datasource.MyBookRemoteDataSource
import app.kaito_dogi.mybrary.core.data.dto.MyBookDto
import app.kaito_dogi.mybrary.core.supabase.input.toMyBookInput
import app.kaito_dogi.mybrary.core.supabase.response.MyBookResponse
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

private const val MY_BOOK_TABLE = "my_book"
private val MY_BOOK_COLUMN_LIST = listOf("*", "book(*)")

internal class DefaultMyBookRemoteDataSource @Inject constructor(
  private val supabaseClient: SupabaseClient,
  @AppDispatcher(AppDispatchers.Io) private val dispatcher: CoroutineDispatcher,
) : MyBookRemoteDataSource {
  override suspend fun getMyBook(myBookId: String): MyBookDto = withContext(dispatcher) {
    val result = supabaseClient.postgrest
      .from(MY_BOOK_TABLE)
      .select(
        columns = Columns.list(MY_BOOK_COLUMN_LIST),
        request = {
          filter {
            MyBookResponse::id eq myBookId
          }
        },
      )
    val myBookResponse = result.decodeSingle<MyBookResponse>()
    return@withContext myBookResponse.toMyBookDto()
  }

  override suspend fun getMyBooks(
    userId: String,
  ): List<MyBookDto> = withContext(dispatcher) {
    // FIXME: 外から渡されたユーザー ID を使用するようにする
    val tempUserId = supabaseClient.auth.currentUserOrNull()?.id ?: throw IllegalStateException("userId is null")
    val result = supabaseClient.postgrest
      .from(MY_BOOK_TABLE)
      .select(
        columns = Columns.list(MY_BOOK_COLUMN_LIST),
        request = {
          filter {
            MyBookResponse::userId eq tempUserId
          }
        },
      )
    val myBookResponseList = result.decodeList<MyBookResponse>()
    return@withContext myBookResponseList.map(MyBookResponse::toMyBookDto)
  }

  override suspend fun postMyBook(postMyBookCommand: PostMyBookCommand): MyBookDto = withContext(dispatcher) {
    // FIXME: 外から渡されたユーザー ID を使用するようにする
    val tempUserId = supabaseClient.auth.currentUserOrNull()?.id ?: throw IllegalStateException("userId is null")
    val tempPostMyBookCommand = postMyBookCommand.copy(userId = tempUserId)
    val myBookInput = tempPostMyBookCommand.toMyBookInput()
    val result = supabaseClient.postgrest
      .from(MY_BOOK_TABLE)
      .insert(
        value = myBookInput,
        request = {
          select(columns = Columns.list(MY_BOOK_COLUMN_LIST))
        },
      )
    val myBookResponse = result.decodeSingle<MyBookResponse>()
    return@withContext myBookResponse.toMyBookDto()
  }

  override suspend fun patchMyBookIsPinned(
    myBookId: String,
    isPinned: Boolean,
  ): MyBookDto = withContext(dispatcher) {
    val result = supabaseClient.postgrest
      .from(MY_BOOK_TABLE)
      .update(
        update = {
          MyBookResponse::isPinned setTo isPinned
        },
        request = {
          filter {
            MyBookResponse::id eq myBookId
          }
          select(columns = Columns.list(MY_BOOK_COLUMN_LIST))
        },
      )
    val myBookResponse = result.decodeSingle<MyBookResponse>()
    return@withContext myBookResponse.toMyBookDto()
  }

  override suspend fun patchMyBookIsFavorite(
    myBookId: String,
    isFavorite: Boolean,
  ): MyBookDto = withContext(dispatcher) {
    val result = supabaseClient.postgrest
      .from(MY_BOOK_TABLE)
      .update(
        update = {
          MyBookResponse::isFavorite setTo isFavorite
        },
        request = {
          filter {
            MyBookResponse::id eq myBookId
          }
          select(columns = Columns.list(MY_BOOK_COLUMN_LIST))
        },
      )
    val myBookResponse = result.decodeSingle<MyBookResponse>()
    return@withContext myBookResponse.toMyBookDto()
  }

  override suspend fun patchMyBookIsPublic(
    myBookId: String,
    isPublic: Boolean,
  ): MyBookDto = withContext(dispatcher) {
    val result = supabaseClient.postgrest
      .from(MY_BOOK_TABLE)
      .update(
        update = {
          MyBookResponse::isPublic setTo isPublic
        },
        request = {
          filter {
            MyBookResponse::id eq myBookId
          }
          select(columns = Columns.list(MY_BOOK_COLUMN_LIST))
        },
      )
    val myBookResponse = result.decodeSingle<MyBookResponse>()
    return@withContext myBookResponse.toMyBookDto()
  }

  override suspend fun patchMyBookIsArchived(
    myBookId: String,
    isArchived: Boolean,
  ): MyBookDto = withContext(dispatcher) {
    val result = supabaseClient.postgrest
      .from(MY_BOOK_TABLE)
      .update(
        update = {
          MyBookResponse::isArchived setTo isArchived
        },
        request = {
          filter {
            MyBookResponse::id eq myBookId
          }
          select(columns = Columns.list(MY_BOOK_COLUMN_LIST))
        },
      )
    val myBookResponse = result.decodeSingle<MyBookResponse>()
    return@withContext myBookResponse.toMyBookDto()
  }

  override suspend fun deleteMyBook(myBookId: String) = withContext(dispatcher) {
    supabaseClient.postgrest
      .from(MY_BOOK_TABLE)
      .delete(
        request = {
          filter {
            MyBookResponse::id eq myBookId
          }
        },
      )
    return@withContext
  }
}
