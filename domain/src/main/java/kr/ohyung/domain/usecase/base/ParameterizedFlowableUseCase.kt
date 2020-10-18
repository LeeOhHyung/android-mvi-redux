/*
 * Created by Lee Oh Hyung on 2020/10/18.
 */
package kr.ohyung.domain.usecase.base

import io.reactivex.Flowable
import io.reactivex.Scheduler

abstract class ParameterizedFlowableUseCase<T, in Params>(
    private val executorThread: Scheduler,
    private val postExecutionThread: Scheduler
) : ParameterizedUseCase<Params>() {

    protected abstract fun buildUseCaseFlowable(params: Params): Flowable<T>

    override fun get(params: Params?): Flowable<T> =
        buildUseCaseFlowable(params = requireParamsNonNull(params))

    override fun execute(params: Params?): Flowable<T> =
        get(params)
            .subscribeOn(executorThread)
            .observeOn(postExecutionThread)
}
