/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.local.source

import io.reactivex.Completable
import io.reactivex.Single
import kr.ohyung.data.model.BookmarksDataModel
import kr.ohyung.data.source.local.BookmarksLocalDataSource
import kr.ohyung.local.dao.BookmarksDao
import kr.ohyung.local.mapper.BookmarksMapper

class BookmarksLocalDataSourceImpl(
    private val bookmarksDao: BookmarksDao,
    private val bookmarksMapper: BookmarksMapper
) : BookmarksLocalDataSource {
    override fun hasItem(id: String) = bookmarksDao.hasItem(id = id)
    override fun insert(dataModel: BookmarksDataModel) = bookmarksDao.insert(bookmarksMapper.toRoomObject(dataModel))
    override fun insert(dataModels: List<BookmarksDataModel>) = bookmarksDao.insert(bookmarksMapper.toRoomObjects(dataModels))
    override fun update(dataModel: BookmarksDataModel) = bookmarksDao.update(bookmarksMapper.toRoomObject(dataModel))
    override fun delete(dataModel: BookmarksDataModel) = bookmarksDao.delete(bookmarksMapper.toRoomObject(dataModel))
    override fun getAll(): Single<List<BookmarksDataModel>> = bookmarksDao.getAll().map { bookmarksMapper.toDataModels(it) }
    override fun getCount(): Single<Int> = bookmarksDao.getCount()
    override fun drop(): Completable = bookmarksDao.drop()
}
