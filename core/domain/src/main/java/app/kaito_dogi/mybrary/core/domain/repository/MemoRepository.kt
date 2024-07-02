package app.kaito_dogi.mybrary.core.domain.repository

import app.kaito_dogi.mybrary.core.domain.model.DraftMemo
import app.kaito_dogi.mybrary.core.domain.model.Memo
import app.kaito_dogi.mybrary.core.domain.model.MemoId
import app.kaito_dogi.mybrary.core.domain.model.MyBookId

interface MemoRepository {
  suspend fun getMemoList(myBookId: MyBookId): List<Memo>

  suspend fun createMemo(draftMemo: DraftMemo): Memo

  suspend fun editMemo(
    memoId: MemoId,
    draftMemo: DraftMemo,
  ): Memo

  suspend fun publishMemo(memoId: MemoId): Memo
}
