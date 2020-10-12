package kr.ohyung.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kr.ohyung.data.mapper.EntityMapper
import kr.ohyung.data.mapper.PhotoEntityMapper
import kr.ohyung.data.model.PhotoSummaryDataModel
import kr.ohyung.domain.entity.PhotoSummary
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun providePhotoEntityMapper(): EntityMapper<PhotoSummaryDataModel, PhotoSummary> = PhotoEntityMapper()
}
