/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.data.source.local

import io.reactivex.Completable
import io.reactivex.Single
import kr.ohyung.data.model.SearchHistoryDataModel

interface SearchHistoryLocalDataSource : RoomDataSource<SearchHistoryDataModel> {
    fun hasItem(keyword: String): Single<Boolean>
    fun delete(keyword: String): Completable
}
