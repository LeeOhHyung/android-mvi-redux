package kr.ohyung.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kr.ohyung.data.mapper.EntityMapper
import kr.ohyung.data.mapper.PhotoEntityMapper
import kr.ohyung.data.model.PhotoSummaryDataModel
import kr.ohyung.remote.ResponseMapper
import kr.ohyung.remote.mapper.PhotosResponseMapper
import kr.ohyung.remote.response.PhotosResponse
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun providePhotosResponseMapper(): ResponseMapper<PhotosResponse, PhotoSummaryDataModel> = PhotosResponseMapper()
}
