package app.kaito_dogi.mybrary.core.data.dto

import app.kaito_dogi.mybrary.core.common.model.Url
import app.kaito_dogi.mybrary.core.domain.model.Book
import app.kaito_dogi.mybrary.core.domain.model.BookId

data class BookDto(
  val id: String,
  val title: String,
  val imageUrl: String,
  val authors: AuthorsDto,
  val publisher: String,
  val isbn: String,
  val genre: GenreDto,
  val rakutenUrl: String,
  val amazonUrl: String?,
) {
  fun toBook() = Book(
    id = BookId(value = this.id),
    title = this.title,
    imageUrl = Url.Image(value = this.imageUrl),
    authorList = this.authors.toAuthorList(),
    publisher = this.publisher,
    isbn = this.isbn,
    genre = this.genre.toGenre(),
    rakutenUrl = Url.Affiliate(value = this.rakutenUrl),
    amazonUrl = this.amazonUrl?.let { Url.Affiliate(value = it) },
  )
}
