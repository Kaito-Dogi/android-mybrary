package app.doggy.mybrary.core.data

import app.doggy.core.common.coroutines.dispatcher.Dispatcher
import app.doggy.core.common.coroutines.dispatcher.MybraryDispatchers
import app.doggy.core.database.dao.RecordDao
import app.doggy.core.database.entity.toEntity
import app.doggy.mybrary.core.domain.model.book.BookId
import app.doggy.mybrary.core.domain.model.record.Record
import app.doggy.mybrary.core.domain.model.record.RecordId
import app.doggy.mybrary.core.domain.repository.RecordRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RecordRepositoryImpl @Inject constructor(
  private val recordDao: RecordDao,
  @Dispatcher(MybraryDispatchers.IO)
  private val ioDispatcher: CoroutineDispatcher,
) : RecordRepository {

  override suspend fun record(
    record: Record,
    bookId: BookId,
  ) {
    withContext(ioDispatcher) {
      recordDao.upsertRecord(record = record.toEntity(bookId))
    }
  }

  override suspend fun updateRecord(
    record: Record,
    bookId: BookId,
  ) {
    withContext(ioDispatcher) {
      recordDao.upsertRecord(record = record.toEntity(bookId))
    }
  }

  override suspend fun deleteRecord(recordId: RecordId) {
    withContext(ioDispatcher) {
      recordDao.deleteRecordById(recordId = recordId.value)
    }
  }

  override fun getRecord(recordId: RecordId): Flow<Record> {
    TODO("Not yet implemented")
  }
}
