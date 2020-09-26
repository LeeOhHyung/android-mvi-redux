/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.ohyung.data.mapper.SearchHistoryEntityMapper
import kr.ohyung.data.source.local.SearchHistoryLocalDataSource
import kr.ohyung.data.source.local.compose
import kr.ohyung.domain.entity.SearchHistory
import kr.ohyung.domain.repository.SearchHistoryRepository

class SearchHistoryRepositoryImpl(
    private val searchHistoryLocalDataSource: SearchHistoryLocalDataSource,
    private val mapper: SearchHistoryEntityMapper
) : SearchHistoryRepository {
    override fun hasItem(keyword: String): Single<Boolean> =
        searchHistoryLocalDataSource.hasItem(keyword = keyword).compose()

    override fun insert(entity: SearchHistory): Completable =
        searchHistoryLocalDataSource.insert(mapper.toDataModel(entity)).compose()

    override fun insert(entities: List<SearchHistory>): Completable =
        searchHistoryLocalDataSource.insert(mapper.toDataModels(entities)).compose()

    override fun update(entity: SearchHistory): Completable =
        searchHistoryLocalDataSource.update(mapper.toDataModel(entity)).compose()

    override fun delete(entity: SearchHistory): Completable =
        searchHistoryLocalDataSource.delete(mapper.toDataModel(entity)).compose()

    override fun delete(keyword: String): Completable =
        searchHistoryLocalDataSource.delete(keyword = keyword).compose()

    override fun getAll(): Single<List<SearchHistory>> =
        searchHistoryLocalDataSource.getAll()
            .map { mapper.toEntities(it) }
            .compose()

    override fun getCount(): Single<Int> =
        searchHistoryLocalDataSource.getCount().compose()

    override fun drop(): Completable =
        searchHistoryLocalDataSource.drop().compose()
}
