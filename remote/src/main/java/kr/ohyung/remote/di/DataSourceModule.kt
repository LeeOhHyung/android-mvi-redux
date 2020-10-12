package kr.ohyung.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kr.ohyung.data.source.remote.PhotoRemoteDataSource
import kr.ohyung.remote.api.PhotosApi
import kr.ohyung.remote.mapper.PhotosResponseMapper
import kr.ohyung.remote.source.PhotoRemoteDataSourceImpl
import javax.inject.Singleton

@Module
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
}
