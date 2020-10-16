/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import kr.ohyung.local.dao.BookmarkDao
import kr.ohyung.local.dao.SearchHistoryDao
import kr.ohyung.local.model.BookmarkRoomObject
import kr.ohyung.local.model.SearchHistoryRoomObject

@Database(
    version = 1,
    entities = [SearchHistoryRoomObject::class, BookmarkRoomObject::class],
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun searchHistoryDao(): SearchHistoryDao
    abstract fun bookmarksDao(): BookmarkDao
}
