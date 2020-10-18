package kr.ohyung.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kr.ohyung.data.mapper.PhotoEntityMapper
import kr.ohyung.data.repository.LocationRepositoryImpl
import kr.ohyung.data.repository.PhotoRepositoryImpl
import kr.ohyung.data.repository.WeatherRepositoryImpl
import kr.ohyung.data.source.local.FusedLocationDataSource
import kr.ohyung.data.source.remote.PhotoRemoteDataSource
import kr.ohyung.data.source.remote.WeatherRemoteDataSource
import kr.ohyung.domain.repository.LocationRepository
import kr.ohyung.domain.repository.PhotoRepository
import kr.ohyung.domain.repository.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePhotoRepository(
        photoRemoteDataSource: PhotoRemoteDataSource,
        photoEntityMapper: PhotoEntityMapper
    ): PhotoRepository = PhotoRepositoryImpl(
        photoRemoteDataSource = photoRemoteDataSource,
        photoEntityMapper = photoEntityMapper
    )

    @Provides
    @Singleton
    fun provideWeatherRepository(
        weatherRemoteDataSource: WeatherRemoteDataSource
    ): WeatherRepository = WeatherRepositoryImpl(weatherRemoteDataSource)

    @Provides
    @Singleton
    fun provideLocationRepository(
        fusedLocationDataSource: FusedLocationDataSource
    ): LocationRepository = LocationRepositoryImpl(fusedLocationDataSource)
}
