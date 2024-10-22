package app.kaito_dogi.mybrary.core.repository.mock

import app.kaito_dogi.mybrary.core.common.coroutines.AppDispatcher
import app.kaito_dogi.mybrary.core.common.coroutines.AppDispatchers
import app.kaito_dogi.mybrary.core.database.DraftMemoDao
import app.kaito_dogi.mybrary.core.domain.model.DraftMemo
import app.kaito_dogi.mybrary.core.domain.model.MyBookId
import app.kaito_dogi.mybrary.core.domain.repository.DraftMemoRepository
import app.kaito_dogi.mybrary.core.repository.mock.convertor.toDraftMemo
import app.kaito_dogi.mybrary.core.repository.mock.convertor.toEntity
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

// FIXME: :core:database への依存を削除し、実際にモックの実装に置き換える
// FIXME: CoroutineDispatcher の注入をやめる
internal class MockDraftMemoRepository @Inject constructor(
  private val draftMemoDao: DraftMemoDao,
  @AppDispatcher(AppDispatchers.Io) private val dispatcher: CoroutineDispatcher,
) : DraftMemoRepository {
  override suspend fun saveDraftMemo(draftMemo: DraftMemo) = withContext(dispatcher) {
    draftMemoDao.upsert(entity = draftMemo.toEntity())
  }

  override suspend fun deleteDraftMemo(myBookId: MyBookId) = withContext(dispatcher) {
    draftMemoDao.deleteByMyBookId(myBookId = myBookId.value)
  }

  override suspend fun getDraftMemo(myBookId: MyBookId): DraftMemo? = withContext(dispatcher) {
    draftMemoDao.getByMyBookId(myBookId = myBookId.value)?.toDraftMemo()
  }
}
