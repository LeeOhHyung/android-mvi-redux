/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.domain.usecase.bookmark

import io.reactivex.Scheduler
import io.reactivex.Single
import kr.ohyung.domain.entity.Bookmark
import kr.ohyung.domain.repository.BookmarkRepository
import kr.ohyung.domain.usecase.base.SingleUseCase

class GetAllBookmarks(
    private val bookmarkRepository: BookmarkRepository,
    executorThread: Scheduler,
    postExecutionThread: Scheduler
) : SingleUseCase<List<Bookmark>>(executorThread, postExecutionThread) {
    override fun buildUseCaseSingle(): Single<List<Bookmark>> = bookmarkRepository.getAll()
}
