package app.kaito_dogi.mybrary.core.data

import app.kaito_dogi.mybrary.core.common.coroutines.dispatcher.Dispatcher
import app.kaito_dogi.mybrary.core.common.coroutines.dispatcher.MybraryDispatchers
import app.kaito_dogi.mybrary.core.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

@Singleton
internal class MockAuthRepository @Inject constructor(
  @Dispatcher(MybraryDispatchers.IO) private val dispatcher: CoroutineDispatcher,
) : AuthRepository {
  override suspend fun hasSession(): Boolean = withContext(dispatcher) {
    return@withContext false
  }
}
