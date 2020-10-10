package kr.ohyung.mvi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kr.ohyung.data.mapper.PhotoEntityMapper
import kr.ohyung.data.repository.PhotoRepositoryImpl
import kr.ohyung.data.source.remote.PhotoRemoteDataSource
import kr.ohyung.domain.repository.PhotoRepository
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
}
