package kr.ohyung.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kr.ohyung.local.dao.BookmarkDao
import kr.ohyung.local.dao.SearchHistoryDao
import kr.ohyung.local.database.LocalDatabase
import javax.inject.Singleton

@Module(
    includes = [MapperModule::class]
)
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    private const val NAME_LOCAL_DATABASE = "local.db"

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext applicationContext: Context): LocalDatabase =
        Room
            .databaseBuilder(applicationContext, LocalDatabase::class.java, NAME_LOCAL_DATABASE)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideSearchHistoryDao(localDatabase: LocalDatabase): SearchHistoryDao = localDatabase.searchHistoryDao()

    @Provides
    @Singleton
    fun provideBookmarkDao(localDatabase: LocalDatabase): BookmarkDao = localDatabase.bookmarksDao()
}
