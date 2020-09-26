/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.local.source

import io.reactivex.Completable
import io.reactivex.Single
import kr.ohyung.data.model.BookmarksDataModel
import kr.ohyung.data.source.local.BookmarkLocalDataSource
import kr.ohyung.local.compose
import kr.ohyung.local.dao.BookmarkDao
import kr.ohyung.local.mapper.BookmarkMapper

class BookmarkLocalDataSourceImpl(
    private val bookmarkDao: BookmarkDao,
    private val bookmarkMapper: BookmarkMapper
) : BookmarkLocalDataSource {
    override fun hasItem(id: String): Single<Boolean> =
        bookmarkDao.hasItem(id = id).compose()

    override fun insert(dataModel: BookmarksDataModel): Completable =
        bookmarkDao.insert(bookmarkMapper.toRoomObject(dataModel)).compose()

    override fun insert(dataModels: List<BookmarksDataModel>): Completable =
        bookmarkDao.insert(bookmarkMapper.toRoomObjects(dataModels)).compose()

    override fun update(dataModel: BookmarksDataModel): Completable =
        bookmarkDao.update(bookmarkMapper.toRoomObject(dataModel)).compose()

    override fun delete(dataModel: BookmarksDataModel): Completable =
        bookmarkDao.delete(bookmarkMapper.toRoomObject(dataModel)).compose()

    override fun delete(id: String): Completable =
        bookmarkDao.delete(id = id).compose()

    override fun getAll(): Single<List<BookmarksDataModel>> =
        bookmarkDao.getAll()
            .map { bookmarkMapper.toDataModels(it) }
            .compose()

    override fun getCount(): Single<Int> =
        bookmarkDao.getCount().compose()

    override fun drop(): Completable =
        bookmarkDao.drop().compose()
}
