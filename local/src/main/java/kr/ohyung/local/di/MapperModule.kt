package kr.ohyung.local.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kr.ohyung.local.mapper.BookmarkMapper
import kr.ohyung.local.mapper.SearchHistoryMapper
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideBookmarkMapper(): BookmarkMapper = BookmarkMapper()

    @Provides
    @Singleton
    fun provideSearchHistoryMapper(): SearchHistoryMapper = SearchHistoryMapper()
}
