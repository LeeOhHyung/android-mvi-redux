/*
 * Created by Lee Oh Hyung on 2020/10/01.
 */
package kr.ohyung.data.mock

import io.reactivex.Completable
import io.reactivex.Single
import kr.ohyung.data.exception.DatabaseException
import kr.ohyung.data.model.SearchHistoryDataModel
import kr.ohyung.data.source.local.SearchHistoryLocalDataSource

class MockSearchHistoryLocalDataSource : SearchHistoryLocalDataSource {

    private val searchHistories = arrayListOf<SearchHistoryDataModel>()

    override fun hasItem(keyword: String): Single<Boolean> = Single.just(searchHistories.any { it.keyword == keyword })

    override fun delete(keyword: String): Completable =
        if(searchHistories.any { it.keyword == keyword }) {
            searchHistories.removeAll { it.keyword == keyword }
            Completable.complete()
        } else {
            Completable.error(DatabaseException.NotFoundException("${keyword}를 가진 검색기록을 찾을 수 없음!!"))
        }

    override fun delete(dataModel: SearchHistoryDataModel): Completable =
        if(searchHistories.any { it.keyword == dataModel.keyword }) {
            searchHistories.removeAll { it.keyword == dataModel.keyword }
            Completable.complete()
        } else {
            Completable.error(DatabaseException.NotFoundException("${dataModel}와 같은 검색기록을 찾을 수 없음!!"))
        }

    override fun insert(dataModel: SearchHistoryDataModel): Completable =
        if(searchHistories.any { it.keyword == dataModel.keyword }.not()) {
            searchHistories.add(dataModel)
            Completable.complete()
        } else {
            Completable.error(DatabaseException.DuplicatedException("${dataModel}는 이미 존재하는 검색기록 입니다!!"))
        }

    override fun insert(dataModels: List<SearchHistoryDataModel>): Completable = run {
        dataModels.map { it.keyword }
            .forEachIndexed { index, keyword ->
                if(searchHistories.any { it.keyword == keyword })
                    return@run Completable.error(DatabaseException.DuplicatedException("${dataModels[index]}는 이미 존재하는 검색기록 입니다!!"))
            }

        searchHistories.addAll(dataModels)
        Completable.complete()
    }

    override fun update(dataModel: SearchHistoryDataModel): Completable =
        if(searchHistories.any { it.id == dataModel.id }) {
            val index = searchHistories.indexOfFirst {
                it.id == dataModel.id
            }
            searchHistories[index] = dataModel
            Completable.complete()
        } else {
            Completable.error(DatabaseException.NotFoundException("${dataModel}을 찾을 수 없음!!"))
        }

    override fun getAll(): Single<List<SearchHistoryDataModel>> = Single.just(searchHistories)

    override fun getCount(): Single<Int> = Single.just(searchHistories.count())

    override fun drop(): Completable = run {
        searchHistories.clear()
        Completable.complete()
    }
}
