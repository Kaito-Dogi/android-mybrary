package app.doggy.newmybrary.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["book_id", "author_id"])
data class BookAuthorCrossRef(
  @ColumnInfo(name = "book_id") val bookId: Int,
  @ColumnInfo(name = "author_id") val authorId: Int,
)
