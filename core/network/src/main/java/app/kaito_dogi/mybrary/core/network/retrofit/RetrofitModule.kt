package app.kaito_dogi.mybrary.core.network.retrofit

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

private const val GoogleBooksBaseUrl = "https://www.googleapis.com/books/v1"

@Module
@InstallIn(SingletonComponent::class)
internal object RetrofitModule {
  @GoogleBooksRetrofit
  @Singleton
  @Provides
  fun providesGoogleBooksRetrofit(
    okHttpClient: OkHttpClient,
  ): Retrofit {
    val contentType = "application/json; charset=UTF8".toMediaType()
    val format = Json { ignoreUnknownKeys = true }
    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl(GoogleBooksBaseUrl)
      .addConverterFactory(format.asConverterFactory(contentType))
      .build()
  }
}
