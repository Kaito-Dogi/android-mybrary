package app.kaito_dogi.mybrary

import app.kaito_dogi.mybrary.core.config.MybraryConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object MybraryConfigModule {
  @Singleton
  @Provides
  fun provideMybraryConfig(): MybraryConfig = object : MybraryConfig {
    override val supabaseUrl: String = BuildConfig.SUPABASE_URL
    override val supabaseKey: String = BuildConfig.SUPABASE_KEY
  }
}
