package app.kaito_dogi.mybrary.core.data.repository

import app.kaito_dogi.mybrary.core.data.model.dummyMemos
import app.kaito_dogi.mybrary.core.domain.model.DraftMemo
import app.kaito_dogi.mybrary.core.domain.model.Memo
import app.kaito_dogi.mybrary.core.domain.model.MemoId
import app.kaito_dogi.mybrary.core.domain.model.MyBookId
import app.kaito_dogi.mybrary.core.domain.repository.MemoRepository
import java.time.LocalDateTime
import javax.inject.Inject
import kotlinx.coroutines.delay

internal class MockMemoRepository @Inject constructor() : MemoRepository {
  override suspend fun getMemos(myBookId: MyBookId): List<Memo> {
    delay(1_000)
    return dummyMemos.filter { it.myBookId == myBookId }
  }

  override suspend fun createMemo(draftMemo: DraftMemo): Boolean {
    return true
  }

  override suspend fun editMemo(
    memoId: MemoId,
    draftMemo: DraftMemo,
  ): Boolean {
    return true
  }

  override suspend fun postMemo(memoId: MemoId): Memo {
    return dummyMemos.first { it.id == memoId }.copy(
        isPosted = false,
        postedAt = LocalDateTime.now(),
        likeCount = 0,
      )
  }
}
