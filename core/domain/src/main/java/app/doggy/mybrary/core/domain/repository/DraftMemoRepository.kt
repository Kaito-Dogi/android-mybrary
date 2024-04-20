package app.doggy.mybrary.core.domain.repository

import app.doggy.mybrary.core.domain.model.DraftMemo
import kotlinx.coroutines.flow.Flow

interface DraftMemoRepository {
  val draftMemos: Flow<List<DraftMemo>>
}
