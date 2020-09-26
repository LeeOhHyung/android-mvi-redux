/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.ohyung.domain.Repository
import kr.ohyung.domain.entity.SearchHistory

interface SearchHistoryRepository : Repository {
    fun hasItem(keyword: String): Single<Boolean>
    fun insert(entity: SearchHistory): Completable
    fun insert(entities: List<SearchHistory>): Completable
    fun update(entity: SearchHistory): Completable
    fun delete(entity: SearchHistory): Completable
    fun delete(keyword: String): Completable
    fun getAll(): Single<List<SearchHistory>>
    fun getCount(): Single<Int>
    fun drop(): Completable
}
