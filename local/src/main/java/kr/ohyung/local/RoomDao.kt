/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Single

interface RoomDao<T: RoomObject> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomObject: T): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomObjects: List<T>): Completable

    @Update
    fun update(roomObject: T): Completable

    @Delete
    fun delete(roomObject: T): Completable
    fun getAll(): Single<List<T>>
    fun getCount(): Single<Int>
    fun drop(): Completable
}
