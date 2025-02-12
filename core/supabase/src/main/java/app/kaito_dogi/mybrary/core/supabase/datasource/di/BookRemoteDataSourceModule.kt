package app.kaito_dogi.mybrary.core.supabase.datasource.di

import app.kaito_dogi.mybrary.core.data.datasource.BookRemoteDataSource
import app.kaito_dogi.mybrary.core.supabase.datasource.DefaultBookRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface BookRemoteDataSourceModule {
  @Singleton
  @Binds
  fun bindBookRemoteDataSource(impl: DefaultBookRemoteDataSource): BookRemoteDataSource
}
