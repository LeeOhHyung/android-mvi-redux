package kr.ohyung.local.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kr.ohyung.data.source.local.BookmarkLocalDataSource
import kr.ohyung.data.source.local.SearchHistoryLocalDataSource
import kr.ohyung.local.dao.BookmarkDao
import kr.ohyung.local.dao.SearchHistoryDao
import kr.ohyung.local.mapper.BookmarkMapper
import kr.ohyung.local.mapper.SearchHistoryMapper
import kr.ohyung.local.source.BookmarkLocalDataSourceImpl
import kr.ohyung.local.source.SearchHistoryLocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideBookmarkLocalDataSource(
        bookmarkDao: BookmarkDao,
        bookmarkMapper: BookmarkMapper
    ): BookmarkLocalDataSource = BookmarkLocalDataSourceImpl(
        bookmarkDao = bookmarkDao,
        bookmarkMapper = bookmarkMapper
    )

    @Provides
    @Singleton
    fun provideSearchHistoryLocalDataSource(
        searchHistoryDao: SearchHistoryDao,
        searchHistoryMapper: SearchHistoryMapper
    ): SearchHistoryLocalDataSource = SearchHistoryLocalDataSourceImpl(
        searchHistoryDao = searchHistoryDao,
        searchHistoryMapper = searchHistoryMapper
    )
}
