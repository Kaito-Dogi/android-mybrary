package app.kaito_dogi.mybrary.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
  @PrimaryKey
  val id: Long,
  val title: String,
  val description: String,
  @ColumnInfo(name = "total_page")
  val totalPage: Int,
  @ColumnInfo(name = "image_url")
  val imageUrl: String,
  @ColumnInfo(name = "registered_at")
  val registeredAt: Long,
  @ColumnInfo(name = "is_pinned")
  val isPinned: Boolean,
  val status: BookStatus,
)

enum class BookStatus {
  WAITING,
  IN_PROGRESS,
  COMPLETED,
}
