package app.kaito_dogi.mybrary.core.common.coroutines

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AppDispatcher(val appDispatchers: AppDispatchers)
