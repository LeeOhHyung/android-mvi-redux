/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.ohyung.domain.Repository
import kr.ohyung.domain.entity.Bookmarks

interface BookmarksRepository : Repository {
    fun hasItem(id: String): Single<Boolean>
    fun insert(entity: Bookmarks): Completable
    fun insert(entities: List<Bookmarks>): Completable
    fun update(entity: Bookmarks): Completable
    fun delete(entity: Bookmarks): Completable
    fun getAll(): Single<List<Bookmarks>>
    fun getCount(): Single<Int>
    fun drop(): Completable
}
