package app.kaito_dogi.mybrary.core.repository

import app.kaito_dogi.mybrary.core.data.command.PatchMemoCommand
import app.kaito_dogi.mybrary.core.data.command.PostMemoCommand
import app.kaito_dogi.mybrary.core.data.datasource.MemoRemoteDataSource
import app.kaito_dogi.mybrary.core.data.dto.MemoDto
import app.kaito_dogi.mybrary.core.domain.model.DraftMemo
import app.kaito_dogi.mybrary.core.domain.model.Memo
import app.kaito_dogi.mybrary.core.domain.model.MemoId
import app.kaito_dogi.mybrary.core.domain.model.MyBookId
import app.kaito_dogi.mybrary.core.domain.repository.MemoRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class MemoRepositoryImpl @Inject constructor(
  private val memoRemoteDataSource: MemoRemoteDataSource,
) : MemoRepository {
  override suspend fun getMemoList(myBookId: MyBookId): List<Memo> {
    val dto = memoRemoteDataSource.getMemos(myBookId = myBookId.value)
    return dto.map(MemoDto::toMemo)
  }

  override suspend fun createMemo(draftMemo: DraftMemo): Memo {
    val dto = memoRemoteDataSource.postMemo(
      command = PostMemoCommand(
        myBookId = draftMemo.myBookId.value,
        content = draftMemo.content,
        startPage = draftMemo.pageRange?.start,
        endPage = draftMemo.pageRange?.end,
      ),
    )
    return dto.toMemo()
  }

  override suspend fun editMemo(
    memoId: MemoId,
    draftMemo: DraftMemo,
  ): Memo {
    val dto = memoRemoteDataSource.patchMemo(
      command = PatchMemoCommand(
        memoId = memoId.value,
        content = draftMemo.content,
        startPage = draftMemo.pageRange?.start,
        endPage = draftMemo.pageRange?.end,
      ),
    )
    return dto.toMemo()
  }

  override suspend fun publishMemo(memoId: MemoId): Memo {
    TODO("Not yet implemented")
  }
}
