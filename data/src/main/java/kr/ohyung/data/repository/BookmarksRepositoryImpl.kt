/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.ohyung.data.mapper.BookmarksEntityMapper
import kr.ohyung.data.source.local.BookmarksLocalDataSource
import kr.ohyung.data.source.local.compose
import kr.ohyung.domain.entity.Bookmarks
import kr.ohyung.domain.repository.BookmarksRepository

class BookmarksRepositoryImpl(
    private val bookmarksLocalDataSource: BookmarksLocalDataSource,
    private val mapper: BookmarksEntityMapper
) : BookmarksRepository {
    override fun hasItem(id: String): Single<Boolean> =
        bookmarksLocalDataSource.hasItem(id = id).compose()

    override fun insert(entity: Bookmarks): Completable =
        bookmarksLocalDataSource.insert(mapper.toDataModel(entity = entity)).compose()

    override fun insert(entities: List<Bookmarks>): Completable =
        bookmarksLocalDataSource.insert(mapper.toDataModels(entities = entities)).compose()

    override fun update(entity: Bookmarks): Completable =
        bookmarksLocalDataSource.update(mapper.toDataModel(entity = entity)).compose()

    override fun delete(entity: Bookmarks): Completable =
        bookmarksLocalDataSource.delete(mapper.toDataModel(entity = entity)).compose()

    override fun getAll(): Single<List<Bookmarks>> =
        bookmarksLocalDataSource.getAll()
            .map { mapper.toEntities(dataModels = it) }
            .compose()

    override fun getCount(): Single<Int> =
        bookmarksLocalDataSource.getCount().compose()

    override fun drop(): Completable =
        bookmarksLocalDataSource.drop().compose()
}
