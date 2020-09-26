/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.domain.usecase.bookmark

import io.reactivex.Completable
import io.reactivex.Scheduler
import kr.ohyung.domain.entity.Bookmark
import kr.ohyung.domain.repository.BookmarkRepository
import kr.ohyung.domain.usecase.base.ParameterizedCompletableUseCase

class AddBookmark(
    private val bookmarkRepository: BookmarkRepository,
    executorThread: Scheduler,
    postExecutionThread: Scheduler
) : ParameterizedCompletableUseCase<Bookmark>(executorThread, postExecutionThread) {
    override fun buildUseCaseCompletable(params: Bookmark): Completable =
        bookmarkRepository.insert(entity = params)
}
