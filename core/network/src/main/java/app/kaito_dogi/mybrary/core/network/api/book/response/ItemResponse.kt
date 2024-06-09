package app.kaito_dogi.mybrary.core.network.api.book.response

import app.kaito_dogi.mybrary.core.common.model.Url
import app.kaito_dogi.mybrary.core.domain.model.Book

internal class ItemResponse(
  private val volumeInfo: VolumeInfoResponse,
) {
  fun toBook(): Book {
    return Book(
      id = "",
      title = volumeInfo.title,
      authors = volumeInfo.authors?.joinToString(", ") ?: "",
      imageUrl = Url.Image(value = volumeInfo.imageLinks?.thumbnail ?: ""),
    )
  }
}
