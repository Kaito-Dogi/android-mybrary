package app.kaito_dogi.mybrary.core.api.mybrary

import app.kaito_dogi.mybrary.core.api.mybrary.response.GetMyBooksResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MybraryApi {
  @GET("/my-books")
  suspend fun getMyBooks(): GetMyBooksResponse

  @GET("/my-books/{id}")
  suspend fun getMyBook(
    @Path("id") myBookId: Long,
  ): GetMyBooksResponse

  @POST("/my-books")
  suspend fun postMyBooks()

  @PUT("/my-books")
  suspend fun putMyBooks()

  @GET("/memos")
  suspend fun getMemos()

  @POST("/memos")
  suspend fun postMemos()

  @PUT("/memos")
  suspend fun putMemos()

  @GET("/users/{user-id}")
  suspend fun getUser()
}
