/*
 * Created by Lee Oh Hyung on 2020/09/27.
 */
package kr.ohyung.domain.usecase.search

import io.reactivex.Scheduler
import io.reactivex.Single
import kr.ohyung.domain.entity.SearchHistory
import kr.ohyung.domain.repository.SearchHistoryRepository
import kr.ohyung.domain.usecase.base.SingleUseCase

class GetAllSearchHistory(
    private val searchHistoryRepository: SearchHistoryRepository,
    executorThread: Scheduler,
    postExecutionThread: Scheduler
) : SingleUseCase<List<SearchHistory>>(executorThread, postExecutionThread) {
    override fun buildUseCaseSingle(): Single<List<SearchHistory>> = searchHistoryRepository.getAll()
}
