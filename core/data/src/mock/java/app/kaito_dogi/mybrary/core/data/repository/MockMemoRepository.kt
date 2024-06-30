package app.kaito_dogi.mybrary.core.data.repository

import app.kaito_dogi.mybrary.core.domain.model.DraftMemo
import app.kaito_dogi.mybrary.core.domain.model.Memo
import app.kaito_dogi.mybrary.core.domain.model.MemoId
import app.kaito_dogi.mybrary.core.domain.model.MyBookId
import app.kaito_dogi.mybrary.core.domain.model.PageRange
import app.kaito_dogi.mybrary.core.domain.model.User
import app.kaito_dogi.mybrary.core.domain.model.UserId
import app.kaito_dogi.mybrary.core.domain.repository.MemoRepository
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

@Singleton
internal class MockMemoRepository @Inject constructor() : MemoRepository {
  private val mockMemoList = MutableStateFlow<List<Memo>>(emptyList())

  override suspend fun getMemoList(myBookId: MyBookId): List<Memo> {
    delay(1_000)

    mockMemoList.update {
      createMockMemoList(myBookId = myBookId)
    }

    return mockMemoList.value
  }

  override suspend fun createMemo(draftMemo: DraftMemo): Memo {
    delay(1_000)

    val createdMemo = Memo(
      id = MemoId(value = mockMemoList.value.size.toLong()),
      user = User(
        id = UserId(value = "userId"),
        name = "ユーザー名",
      ),
      myBookId = draftMemo.myBookId,
      content = draftMemo.content,
      pageRange = draftMemo.pageRange,
      createdAt = LocalDateTime.now(),
      updatedAt = null,
      publishedAt = null,
      likeCount = 0,
    )
    mockMemoList.update { it + createdMemo }

    return createdMemo
  }

  override suspend fun updateMemo(
    memoId: MemoId,
    draftMemo: DraftMemo,
  ): Memo {
    delay(1_000)

    val memo = mockMemoList.value.first { it.id == memoId }
    val updatedMemo = memo.copy(
      content = draftMemo.content,
      pageRange = draftMemo.pageRange,
      updatedAt = LocalDateTime.now(),
    )
    val newMemoList = mockMemoList.value.map {
      if (it.id == memoId) updatedMemo else it
    }
    mockMemoList.update { newMemoList }

    return updatedMemo
  }

  override suspend fun publishMemo(memoId: MemoId): Memo {
    delay(1_000)

    val memo = mockMemoList.value.first { it.id == memoId }
    val publishedMemo = memo.copy(
      publishedAt = LocalDateTime.now(),
    )
    val newMemoList = mockMemoList.value.map {
      if (it.id == memoId) publishedMemo else it
    }
    mockMemoList.update { newMemoList }

    return publishedMemo
  }
}

private fun createMockMemoList(myBookId: MyBookId) = List(10) { index ->
  val fromPage = if (index % 2 == 0) index * 100 else null
  val toPage = if (index % 4 == 0) (index + 1) * 100 else null
  Memo(
    id = MemoId(value = index.toLong()),
    user = User(
      id = UserId(value = "userId"),
      name = "ユーザー名",
    ),
    myBookId = myBookId,
    content = "メモ$index",
    pageRange = fromPage?.let {
      PageRange(
        from = it,
        to = toPage,
      )
    },
    createdAt = LocalDateTime.now(),
    updatedAt = null,
    publishedAt = null,
    likeCount = 0,
  )
}
