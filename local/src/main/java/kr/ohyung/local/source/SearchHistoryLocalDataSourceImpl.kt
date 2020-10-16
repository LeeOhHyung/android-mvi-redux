/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.local.source

import io.reactivex.Completable
import io.reactivex.Single
import kr.ohyung.data.model.SearchHistoryDataModel
import kr.ohyung.data.source.local.SearchHistoryLocalDataSource
import kr.ohyung.local.compose
import kr.ohyung.local.dao.SearchHistoryDao
import kr.ohyung.local.mapper.SearchHistoryMapper
import javax.inject.Inject

class SearchHistoryLocalDataSourceImpl @Inject constructor(
    private val searchHistoryDao: SearchHistoryDao,
    private val searchHistoryMapper: SearchHistoryMapper
) : SearchHistoryLocalDataSource {
    override fun hasItem(keyword: String): Single<Boolean> =
        searchHistoryDao.hasItem(keyword = keyword).compose()

    override fun insert(dataModel: SearchHistoryDataModel): Completable =
        searchHistoryDao.insert(searchHistoryMapper.toRoomObject(dataModel)).compose()

    override fun insert(dataModels: List<SearchHistoryDataModel>): Completable =
        searchHistoryDao.insert(searchHistoryMapper.toRoomObjects(dataModels)).compose()

    override fun update(dataModel: SearchHistoryDataModel): Completable =
        searchHistoryDao.update(searchHistoryMapper.toRoomObject(dataModel)).compose()

    override fun delete(dataModel: SearchHistoryDataModel): Completable =
        searchHistoryDao.delete(searchHistoryMapper.toRoomObject(dataModel)).compose()

    override fun delete(keyword: String): Completable =
        searchHistoryDao.delete(keyword = keyword).compose()

    override fun getAll(): Single<List<SearchHistoryDataModel>> =
        searchHistoryDao.getAll()
            .map { searchHistoryMapper.toDataModels(it) }
            .compose()

    override fun getCount(): Single<Int> =
        searchHistoryDao.getCount().compose()

    override fun drop(): Completable =
        searchHistoryDao.drop().compose()
}
