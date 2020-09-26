/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.local.source

import io.reactivex.Completable
import io.reactivex.Single
import kr.ohyung.data.model.BookmarksDataModel
import kr.ohyung.data.source.local.BookmarksLocalDataSource
import kr.ohyung.local.compose
import kr.ohyung.local.dao.BookmarksDao
import kr.ohyung.local.mapper.BookmarksMapper

class BookmarksLocalDataSourceImpl(
    private val bookmarksDao: BookmarksDao,
    private val bookmarksMapper: BookmarksMapper
) : BookmarksLocalDataSource {
    override fun hasItem(id: String): Single<Boolean> =
        bookmarksDao.hasItem(id = id).compose()

    override fun insert(dataModel: BookmarksDataModel): Completable =
        bookmarksDao.insert(bookmarksMapper.toRoomObject(dataModel)).compose()

    override fun insert(dataModels: List<BookmarksDataModel>): Completable =
        bookmarksDao.insert(bookmarksMapper.toRoomObjects(dataModels)).compose()

    override fun update(dataModel: BookmarksDataModel): Completable =
        bookmarksDao.update(bookmarksMapper.toRoomObject(dataModel)).compose()

    override fun delete(dataModel: BookmarksDataModel): Completable =
        bookmarksDao.delete(bookmarksMapper.toRoomObject(dataModel)).compose()

    override fun getAll(): Single<List<BookmarksDataModel>> =
        bookmarksDao.getAll()
            .map { bookmarksMapper.toDataModels(it) }
            .compose()

    override fun getCount(): Single<Int> =
        bookmarksDao.getCount().compose()

    override fun drop(): Completable =
        bookmarksDao.drop().compose()
}
