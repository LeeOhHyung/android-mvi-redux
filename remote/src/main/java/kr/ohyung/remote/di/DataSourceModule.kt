package kr.ohyung.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kr.ohyung.data.source.remote.PhotoRemoteDataSource
import kr.ohyung.data.source.remote.WeatherRemoteDataSource
import kr.ohyung.remote.api.PhotosApi
import kr.ohyung.remote.api.ReverseGeocodingApi
import kr.ohyung.remote.api.WeatherApi
import kr.ohyung.remote.mapper.PhotosResponseMapper
import kr.ohyung.remote.source.PhotoRemoteDataSourceImpl
import kr.ohyung.remote.source.WeatherRemoteDataSourceImpl
import javax.inject.Singleton

@Module(
    includes = [
        ApiModule::class,
        MapperModule::class,
        RetrofitModule::class
    ]
)
@InstallIn(ApplicationComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun providePhotoRemoteDataSource(
        photosApi: PhotosApi,
        photosResponseMapper: PhotosResponseMapper
    ): PhotoRemoteDataSource = PhotoRemoteDataSourceImpl(
        photosApi = photosApi,
        photosResponseMapper = photosResponseMapper
    )

    @Provides
    @Singleton
    fun provideWeatherRemoteDataSource(
        reverseGeocodingApi: ReverseGeocodingApi,
        weatherApi: WeatherApi
    ): WeatherRemoteDataSource = WeatherRemoteDataSourceImpl(
        reverseGeocodingApi = reverseGeocodingApi,
        weatherApi = weatherApi
    )
}
