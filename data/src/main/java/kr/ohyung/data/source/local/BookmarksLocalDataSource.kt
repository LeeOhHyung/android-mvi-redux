/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.data.source.local

import io.reactivex.Single
import kr.ohyung.data.model.BookmarksDataModel

interface BookmarksLocalDataSource : LocalDataSource<BookmarksDataModel> {
    fun hasItem(id: String): Single<Boolean>
}
