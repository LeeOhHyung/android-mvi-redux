/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.domain.usecase.search

import io.reactivex.Completable
import io.reactivex.Scheduler
import kr.ohyung.domain.entity.SearchHistory
import kr.ohyung.domain.repository.SearchHistoryRepository
import kr.ohyung.domain.usecase.base.ParameterizedCompletableUseCase

class AddSearchHistory(
    private val searchHistoryRepository: SearchHistoryRepository,
    executorThread: Scheduler,
    postExecutionThread: Scheduler
) : ParameterizedCompletableUseCase<SearchHistory>(executorThread, postExecutionThread) {
    override fun buildUseCaseCompletable(params: SearchHistory): Completable =
        searchHistoryRepository.insert(entity = params)
}
