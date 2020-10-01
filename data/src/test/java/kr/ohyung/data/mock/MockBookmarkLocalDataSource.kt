/*
 * Created by Lee Oh Hyung on 2020/10/01.
 */
package kr.ohyung.data.mock

import io.reactivex.Completable
import io.reactivex.Single
import kr.ohyung.data.exception.DatabaseException
import kr.ohyung.data.model.BookmarkDataModel
import kr.ohyung.data.source.local.BookmarkLocalDataSource

class MockBookmarkLocalDataSource : BookmarkLocalDataSource {

    private val bookmarks = arrayListOf<BookmarkDataModel>()

    override fun hasItem(id: String): Single<Boolean> = Single.just(bookmarks.any { it.id == id })

    override fun delete(id: String): Completable =
        if(bookmarks.any { it.id == id }) {
            bookmarks.removeAll { it.id == id }
            Completable.complete()
        } else {
            Completable.error(DatabaseException.NotFoundException("result is empty"))
        }

    override fun delete(dataModel: BookmarkDataModel): Completable =
        if(bookmarks.any { it.id == dataModel.id }) {
            bookmarks.removeAll { it.id == dataModel.id }
            Completable.complete()
        } else {
            Completable.error(DatabaseException.NotFoundException("result is empty"))
        }

    override fun insert(dataModel: BookmarkDataModel): Completable =
        if(bookmarks.any { it.id == dataModel.id }.not()) {
            bookmarks.add(dataModel)
            Completable.complete()
        } else {
            Completable.error(DatabaseException.DuplicatedException("$dataModel is duplicated"))
        }

    override fun insert(dataModels: List<BookmarkDataModel>): Completable = run{
        dataModels.map { it.id }
            .forEachIndexed { index, newId ->
                if(bookmarks.any { it.id ==  newId })
                    return@run Completable.error(DatabaseException.DuplicatedException("${dataModels[index]} is duplicated"))
            }

        bookmarks.addAll(dataModels)
        Completable.complete()
    }

    override fun update(dataModel: BookmarkDataModel): Completable =
        if(bookmarks.any { it.id == dataModel.id }) {
            val index = bookmarks.indexOfFirst { it.id == dataModel.id }
            bookmarks[index] = dataModel
            Completable.complete()
        } else {
            Completable.error(DatabaseException.NotFoundException("result is empty"))
        }

    override fun getAll(): Single<List<BookmarkDataModel>> = Single.just(bookmarks)

    override fun getCount(): Single<Int> = Single.just(bookmarks.count())

    override fun drop(): Completable = run {
        bookmarks.clear()
        Completable.complete()
    }
}
