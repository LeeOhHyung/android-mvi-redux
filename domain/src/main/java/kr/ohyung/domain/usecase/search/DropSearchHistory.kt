/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.domain.usecase.search

import io.reactivex.Completable
import io.reactivex.Scheduler
import kr.ohyung.domain.repository.SearchHistoryRepository
import kr.ohyung.domain.usecase.base.CompletableUseCase

class DropSearchHistory(
    private val searchHistoryRepository: SearchHistoryRepository,
    executorThread: Scheduler,
    postExecutionThread: Scheduler
) : CompletableUseCase(executorThread, postExecutionThread) {
    override fun buildUseCaseCompletable(): Completable = searchHistoryRepository.drop()
}
