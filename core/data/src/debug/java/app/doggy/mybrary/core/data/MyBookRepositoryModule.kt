package app.doggy.mybrary.core.data

import app.doggy.mybrary.core.data.repository.MyBookRepositoryImpl
import app.doggy.mybrary.core.domain.repository.MyBookRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface MyBookRepositoryModule {

  @Binds
  fun bindsMyBookRepository(
    myBookRepository: MyBookRepositoryImpl,
  ): MyBookRepository
}
