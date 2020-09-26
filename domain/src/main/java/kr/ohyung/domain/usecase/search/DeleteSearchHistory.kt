/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.domain.usecase.search

import io.reactivex.Completable
import io.reactivex.Scheduler
import kr.ohyung.domain.repository.SearchHistoryRepository
import kr.ohyung.domain.usecase.base.ParameterizedCompletableUseCase

class DeleteSearchHistory(
    private val searchHistoryRepository: SearchHistoryRepository,
    executorThread: Scheduler,
    postExecutionThread: Scheduler
) : ParameterizedCompletableUseCase<String>(executorThread, postExecutionThread) {
    override fun buildUseCaseCompletable(params: String): Completable =
        searchHistoryRepository.delete(keyword = params)
}
