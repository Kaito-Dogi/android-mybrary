package app.doggy.mybrary.core.data.repository

import app.doggy.mybrary.core.data.model.dummyMyBooks
import app.doggy.mybrary.core.domain.model.MyBook
import app.doggy.mybrary.core.domain.model.MyBookId
import app.doggy.mybrary.core.domain.repository.MyBookRepository
import javax.inject.Inject

internal class MockMyBookRepository @Inject constructor() : MyBookRepository {
  override suspend fun getMyBooks(): List<MyBook> {
    println("あああ: mock")
    return dummyMyBooks
  }

  override suspend fun getMyBook(
    myBookId: MyBookId,
  ): MyBook {
    return dummyMyBooks.first { it.id == myBookId }
  }

  override suspend fun registerBook(
    externalBookId: String,
  ): Boolean {
    return true
  }

  override suspend fun pinBook(
    myBookId: MyBookId,
  ): MyBook {
    return dummyMyBooks
      .first { it.id == myBookId }
      .copy(isPinned = true)
  }

  override suspend fun makeBookFavorite(
    myBookId: MyBookId,
  ): MyBook {
    return dummyMyBooks
      .first { it.id == myBookId }
      .copy(isFavorite = true)
  }

  override suspend fun archiveBook(
    myBookId: MyBookId,
  ): MyBook {
    return dummyMyBooks
      .first { it.id == myBookId }
      .copy(isArchived = true)
  }
}
