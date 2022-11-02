package app.doggy.newmybrary.data.api.service

import app.doggy.newmybrary.data.api.RetrofitManager
import app.doggy.newmybrary.data.api.response.FetchBooksResponse
import javax.inject.Inject
import retrofit2.Response
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

class BookApi @Inject constructor(
  private val retrofitManager: RetrofitManager,
) {
  val service = retrofitManager.retrofit.create<BookService>()
}

interface BookService {
  @GET("volumes")
  suspend fun fetchBookByIsbn(@Query("q") isbn: String): Response<FetchBooksResponse>
}
