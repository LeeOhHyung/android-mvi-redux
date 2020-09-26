/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kr.ohyung.local.dao.BookmarksDao
import kr.ohyung.local.dao.SearchHistoryDao
import kr.ohyung.local.model.BookmarksRoomObject
import kr.ohyung.local.model.SearchHistoryRoomObject

@Database(
    version = 1,
    entities = [SearchHistoryRoomObject::class, BookmarksRoomObject::class],
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun searchHistoryDao(): SearchHistoryDao
    abstract fun bookmarksDao(): BookmarksDao

    companion object {
        private var instance: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase {
            if(instance == null) {
                synchronized(LocalDatabase::class) {
                    instance = Room
                        .databaseBuilder(context.applicationContext, LocalDatabase::class.java, "local.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance!!
        }

        fun destroy() {
            instance = null
        }
    }
}
