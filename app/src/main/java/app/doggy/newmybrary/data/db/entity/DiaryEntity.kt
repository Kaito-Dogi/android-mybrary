package app.doggy.newmybrary.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class DiaryEntity(
  @PrimaryKey val id: Int,
  @ColumnInfo(name = "book_id") val bookId: Int,
  val content: String,
  @ColumnInfo(name = "current_page") val currentPage: Int,
  @ColumnInfo(name = "created_at") val createdAt: Date,
)
