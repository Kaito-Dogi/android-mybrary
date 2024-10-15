package app.kaito_dogi.mybrary.core.supabase

import app.kaito_dogi.mybrary.core.config.AppConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import javax.inject.Singleton
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
internal object SupabaseClientModule {
  @Singleton
  @Provides
  fun provideSupabaseClient(appConfig: AppConfig): SupabaseClient = createSupabaseClient(
    supabaseUrl = appConfig.supabaseUrl,
    supabaseKey = appConfig.supabaseKey,
  ) {
    defaultSerializer = KotlinXSerializer(
      json = Json { ignoreUnknownKeys = true },
    )
    install(Postgrest)
    install(Auth)
  }
}
