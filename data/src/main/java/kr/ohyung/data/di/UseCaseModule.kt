package kr.ohyung.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kr.ohyung.domain.executor.ExecutorProvider
import kr.ohyung.domain.repository.LocationRepository
import kr.ohyung.domain.repository.PhotoRepository
import kr.ohyung.domain.repository.WeatherRepository
import kr.ohyung.domain.usecase.GetCurrentLegalNameUseCase
import kr.ohyung.domain.usecase.GetCurrentLocationForecastUseCase
import kr.ohyung.domain.usecase.GetRandomPhotoUseCase
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetRandomPhotoUseCase(
        photoRepository: PhotoRepository,
        executorProvider: ExecutorProvider
    ): GetRandomPhotoUseCase = GetRandomPhotoUseCase(
        photoRepository = photoRepository,
        executorThread = executorProvider.io(),
        postExecutionThread = executorProvider.mainThread()
    )

    @Provides
    @Singleton
    fun provideGetCurrentLegalNameUseCase(
        locationRepository: LocationRepository,
        weatherRepository: WeatherRepository,
        executorProvider: ExecutorProvider
    ): GetCurrentLegalNameUseCase = GetCurrentLegalNameUseCase(
        locationRepository = locationRepository,
        weatherRepository =  weatherRepository,
        executorProvider = executorProvider
    )

    @Provides
    @Singleton
    fun provideGetCurrentLocationForecastUseCase(
        locationRepository: LocationRepository,
        weatherRepository: WeatherRepository,
        executorProvider: ExecutorProvider
    ): GetCurrentLocationForecastUseCase = GetCurrentLocationForecastUseCase(
        locationRepository = locationRepository,
        weatherRepository = weatherRepository,
        executorProvider = executorProvider
    )
}
