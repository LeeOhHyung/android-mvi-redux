package kr.ohyung.local.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kr.ohyung.data.source.local.BookmarkLocalDataSource
import kr.ohyung.data.source.local.FusedLocationDataSource
import kr.ohyung.data.source.local.SearchHistoryLocalDataSource
import kr.ohyung.local.dao.BookmarkDao
import kr.ohyung.local.dao.SearchHistoryDao
import kr.ohyung.local.mapper.BookmarkMapper
import kr.ohyung.local.mapper.SearchHistoryMapper
import kr.ohyung.local.source.BookmarkLocalDataSourceImpl
import kr.ohyung.local.source.FusedLocationDataSourceImpl
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

    @Provides
    @Singleton
    fun provideFusedLocationDataSource(
        fusedLocationProviderClient: FusedLocationProviderClient
    ): FusedLocationDataSource = FusedLocationDataSourceImpl(
        fusedLocationProviderClient = fusedLocationProviderClient
    )

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(
        @ApplicationContext context: Context
    ): FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
}
