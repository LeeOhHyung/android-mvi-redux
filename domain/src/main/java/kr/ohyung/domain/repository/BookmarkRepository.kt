/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.ohyung.domain.Repository
import kr.ohyung.domain.entity.Bookmark

interface BookmarkRepository : Repository {
    fun hasItem(id: String): Single<Boolean>
    fun insert(entity: Bookmark): Completable
    fun insert(entities: List<Bookmark>): Completable
    fun update(entity: Bookmark): Completable
    fun delete(entity: Bookmark): Completable
    fun delete(id: String): Completable
    fun getAll(): Single<List<Bookmark>>
    fun getCount(): Single<Int>
    fun drop(): Completable
}
