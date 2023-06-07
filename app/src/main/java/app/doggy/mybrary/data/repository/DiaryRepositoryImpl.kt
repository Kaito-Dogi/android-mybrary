package app.doggy.mybrary.data.repository

import app.doggy.mybrary.data.db.MybraryDatabase
import app.doggy.mybrary.data.db.entity.toDiaryEntity
import app.doggy.mybrary.data.repository.fake.FakeDiaryRepository
import app.doggy.mybrary.core.domain.model.Diary
import app.doggy.mybrary.core.domain.repository.DiaryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// FIXME: Hilt モジュールの置き場所を考える
@InstallIn(SingletonComponent::class)
@Module
internal interface DiaryRepositoryModule {
  // fun DiaryRepositoryImpl.bindDiaryRepository(): DiaryRepository
  @Binds
  fun FakeDiaryRepository.bindDiaryRepository(): DiaryRepository
}

class DiaryRepositoryImpl @Inject constructor(
  private val db: MybraryDatabase,
) : DiaryRepository {
  override suspend fun recordDiary(
    diary: Diary,
    bookId: Long,
  ): Boolean = withContext(Dispatchers.IO) {
    db.diaryDao().insert(diary.toDiaryEntity(bookId))
    true
  }
}
