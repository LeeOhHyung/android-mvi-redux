/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.data.source.local

import io.reactivex.Completable
import io.reactivex.Single
import kr.ohyung.data.model.SearchHistoryDataModel

interface SearchHistoryLocalDataSource : LocalDataSource<SearchHistoryDataModel> {
    override fun insert(dataModel: SearchHistoryDataModel): Completable
    override fun insert(dataModels: List<SearchHistoryDataModel>): Completable
    override fun update(dataModel: SearchHistoryDataModel): Completable
    override fun delete(dataModel: SearchHistoryDataModel): Completable
    override fun getAll(): Single<List<SearchHistoryDataModel>>
    override fun getCount(): Single<Int>
    override fun drop(): Completable
}
