package app.doggy.newmybrary.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.doggy.newmybrary.domain.model.Book
import java.util.Date

@Entity(tableName = "books")
data class BookEntity(
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "book_id")
  val bookId: Long,

  @ColumnInfo(name = "books_api_id")
  val booksApiId: String,

  val title: String,

  val description: String,

  @ColumnInfo(name = "total_page")
  val totalPage: Int,

  @ColumnInfo(name = "image_url")
  val imageUrl: String,

  @ColumnInfo(name = "created_at")
  val createdAt: Date,
)

fun Book.toBookEntity() = BookEntity(
  bookId = 0L,
  booksApiId = this.id,
  title = this.title,
  description = this.description,
  totalPage = this.totalPage,
  imageUrl = this.imageUrl,
  createdAt = Date(),
)
