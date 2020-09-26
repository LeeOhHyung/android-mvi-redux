/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.ohyung.data.mapper.BookmarkEntityMapper
import kr.ohyung.data.source.local.BookmarkLocalDataSource
import kr.ohyung.data.source.local.compose
import kr.ohyung.domain.entity.Bookmark
import kr.ohyung.domain.repository.BookmarkRepository

class BookmarkRepositoryImpl(
    private val bookmarkLocalDataSource: BookmarkLocalDataSource,
    private val mapper: BookmarkEntityMapper
) : BookmarkRepository {
    override fun hasItem(id: String): Single<Boolean> =
        bookmarkLocalDataSource.hasItem(id = id).compose()

    override fun insert(entity: Bookmark): Completable =
        bookmarkLocalDataSource.insert(mapper.toDataModel(entity = entity)).compose()

    override fun insert(entities: List<Bookmark>): Completable =
        bookmarkLocalDataSource.insert(mapper.toDataModels(entities = entities)).compose()

    override fun update(entity: Bookmark): Completable =
        bookmarkLocalDataSource.update(mapper.toDataModel(entity = entity)).compose()

    override fun delete(entity: Bookmark): Completable =
        bookmarkLocalDataSource.delete(mapper.toDataModel(entity = entity)).compose()

    override fun delete(id: String): Completable =
        bookmarkLocalDataSource.delete(id = id).compose()

    override fun getAll(): Single<List<Bookmark>> =
        bookmarkLocalDataSource.getAll()
            .map { mapper.toEntities(dataModels = it) }
            .compose()

    override fun getCount(): Single<Int> =
        bookmarkLocalDataSource.getCount().compose()

    override fun drop(): Completable =
        bookmarkLocalDataSource.drop().compose()
}
