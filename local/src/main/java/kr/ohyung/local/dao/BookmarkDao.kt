/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.local.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import kr.ohyung.local.RoomDao
import kr.ohyung.local.model.BookmarkRoomObject

@Dao
interface BookmarkDao : RoomDao<BookmarkRoomObject> {

    @Query("SELECT * FROM bookmarks")
    override fun getAll(): Single<List<BookmarkRoomObject>>

    @Query("SELECT COUNT(*) FROM bookmarks")
    override fun getCount(): Single<Int>

    @Query("DELETE FROM bookmarks")
    override fun drop(): Completable

    @Query("SELECT EXISTS(SELECT * FROM bookmarks WHERE id = :id)")
    fun hasItem(id: String): Single<Boolean>

    @Query("DELETE FROM bookmarks WHERE id = :id")
    fun delete(id: String): Completable

    @Query("SELECT * FROM bookmarks WHERE id = :id")
    fun get(id: String): Single<List<BookmarkRoomObject>>
}
