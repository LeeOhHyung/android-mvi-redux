/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.domain.usecase.bookmark

import io.reactivex.Completable
import io.reactivex.Scheduler
import kr.ohyung.domain.repository.BookmarkRepository
import kr.ohyung.domain.usecase.base.ParameterizedCompletableUseCase

class DeleteBookmark(
    private val bookmarkRepository: BookmarkRepository,
    executorThread: Scheduler,
    postExecutionThread: Scheduler
) : ParameterizedCompletableUseCase<String>(executorThread, postExecutionThread) {
    override fun buildUseCaseCompletable(params: String): Completable =
        bookmarkRepository.delete(id = params)
}