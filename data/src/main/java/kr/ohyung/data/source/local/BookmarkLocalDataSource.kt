/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.data.source.local

import io.reactivex.Completable
import io.reactivex.Single
import kr.ohyung.data.model.BookmarkDataModel

interface BookmarkLocalDataSource : LocalDataSource<BookmarkDataModel> {
    fun hasItem(id: String): Single<Boolean>
    fun delete(id: String): Completable
}
