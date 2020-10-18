/*
 * Created by Lee Oh Hyung on 2020/10/18.
 */
package kr.ohyung.domain.usecase.base

import io.reactivex.Flowable
import io.reactivex.Scheduler

abstract class FlowableUseCase<T>(
    private val executorThread: Scheduler,
    private val postExecutionThread: Scheduler
) : NoParamsUseCase() {

    protected abstract fun buildUseCaseFlowable(): Flowable<T>

    override fun execute(): Flowable<T> =
        buildUseCaseFlowable()
            .subscribeOn(executorThread)
            .observeOn(postExecutionThread)
}
