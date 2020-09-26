/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.local.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import kr.ohyung.local.RoomDao
import kr.ohyung.local.model.SearchHistoryRoomObject

@Dao
interface SearchHistoryDao : RoomDao<SearchHistoryRoomObject> {

    @Query("SELECT * FROM search_history")
    override fun getAll(): Single<List<SearchHistoryRoomObject>>

    @Query("SELECT COUNT(*) FROM search_history")
    override fun getCount(): Single<Int>

    @Query("DELETE FROM search_history")
    override fun drop(): Completable

    @Query("SELECT EXISTS(SELECT * FROM search_history WHERE keyword = :keyword)")
    fun hasItem(keyword: String): Single<Boolean>

    @Query("DELETE FROM search_history WHERE keyword = :keyword")
    fun delete(keyword: String): Completable
}
