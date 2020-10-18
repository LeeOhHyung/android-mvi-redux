package kr.ohyung.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kr.ohyung.remote.BuildConfig
import kr.ohyung.remote.api.PhotosApi
import kr.ohyung.remote.api.ReverseGeocodingApi
import kr.ohyung.remote.api.WeatherApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providePhotosApi(
        builder: Retrofit.Builder,
        okHttpClientBuilder: OkHttpClient.Builder
    ): PhotosApi =
        builder
            .baseUrl(BuildConfig.BASE_URL_UNSPLASH)
            .client(okHttpClientBuilder.build())
            .build()
            .create(PhotosApi::class.java)

    @Provides
    @Singleton
    fun provideWeatherApi(
        builder: Retrofit.Builder,
        okHttpClientBuilder: OkHttpClient.Builder
    ): WeatherApi =
        builder
            .baseUrl(BuildConfig.BASE_URL_OPEN_WEATHER_MAP)
            .client(okHttpClientBuilder.build())
            .build()
            .create(WeatherApi::class.java)

    @Provides
    @Singleton
    fun provideReverseGeocodingApi(
        builder: Retrofit.Builder,
        okHttpClientBuilder: OkHttpClient.Builder,
        @Named("navermap") headerInterceptor: Interceptor
    ): ReverseGeocodingApi =
        builder
            .baseUrl(BuildConfig.BASE_URL_NAVER_MAP)
            .client(
                okHttpClientBuilder
                    .addInterceptor(headerInterceptor)
                    .build()
            )
            .build()
            .create(ReverseGeocodingApi::class.java)

}
