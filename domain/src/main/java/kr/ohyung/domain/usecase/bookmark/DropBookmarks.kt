/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.domain.usecase.bookmark

import io.reactivex.Completable
import io.reactivex.Scheduler
import kr.ohyung.domain.repository.BookmarkRepository
import kr.ohyung.domain.usecase.base.CompletableUseCase

class DropBookmarks(
    private val bookmarkRepository: BookmarkRepository,
    executorThread: Scheduler,
    postExecutionThread: Scheduler
) : CompletableUseCase(executorThread, postExecutionThread) {
    override fun buildUseCaseCompletable(): Completable = bookmarkRepository.drop()
}
