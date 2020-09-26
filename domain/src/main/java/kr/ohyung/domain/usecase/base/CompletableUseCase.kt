/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.domain.usecase.base

import io.reactivex.Completable
import io.reactivex.Scheduler

abstract class CompletableUseCase(
    private val executorThread: Scheduler,
    private val postExecutionThread: Scheduler
) : NoParamsUseCase() {

    protected abstract fun buildUseCaseCompletable(): Completable

    override fun execute(): Completable =
        buildUseCaseCompletable()
            .subscribeOn(executorThread)
            .observeOn(postExecutionThread)
}
