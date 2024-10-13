package app.kaito_dogi.mybrary.core.supabase.response

import app.kaito_dogi.mybrary.core.data.dto.BookDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class BookResponse(
  @SerialName("id")
  val bookId: String,
  val title: String,
  @SerialName("image_url")
  val imageUrl: String,
  val authors: AuthorsResponse,
  val publisher: String,
  val isbn: String,
  val genre: GenreResponse,
  @SerialName("rakuten_url")
  val rakutenUrl: String,
  @SerialName("amazon_url")
  val amazonUrl: String? = null,
) {
  fun toBookDto() = BookDto(
    bookId = this.bookId,
    title = this.title,
    imageUrl = this.imageUrl,
    authors = this.authors.toAuthorsDto(),
    publisher = this.publisher,
    isbn = this.isbn,
    genre = this.genre.toGenreDto(),
    rakutenUrl = this.rakutenUrl,
    amazonUrl = this.amazonUrl,
  )
}
