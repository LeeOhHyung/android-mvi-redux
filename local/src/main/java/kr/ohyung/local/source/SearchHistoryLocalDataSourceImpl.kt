/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.local.source

import kr.ohyung.data.model.SearchHistoryDataModel
import kr.ohyung.data.source.local.SearchHistoryLocalDataSource
import kr.ohyung.local.dao.SearchHistoryDao
import kr.ohyung.local.mapper.SearchHistoryMapper

class SearchHistoryLocalDataSourceImpl(
    private val searchHistoryDao: SearchHistoryDao,
    private val searchHistoryMapper: SearchHistoryMapper
) : SearchHistoryLocalDataSource {

    override fun insert(dataModel: SearchHistoryDataModel) = searchHistoryDao.insert(searchHistoryMapper.toRoomObject(dataModel))
    override fun insert(dataModels: List<SearchHistoryDataModel>) = searchHistoryDao.insert(searchHistoryMapper.toRoomObjects(dataModels))
    override fun update(dataModel: SearchHistoryDataModel) = searchHistoryDao.update(searchHistoryMapper.toRoomObject(dataModel))
    override fun delete(dataModel: SearchHistoryDataModel) = searchHistoryDao.delete(searchHistoryMapper.toRoomObject(dataModel))
    override fun getAll() = searchHistoryDao.getAll().map { searchHistoryMapper.toDataModels(it) }
    override fun getCount() = searchHistoryDao.getCount()
    override fun drop() = searchHistoryDao.drop()
}
