/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.domain.usecase.base

import io.reactivex.Scheduler
import io.reactivex.Single

abstract class ParameterizedSingleUseCase<T, in Params>(
    private val executorThread: Scheduler,
    private val postExecutionThread: Scheduler
) : ParameterizedUseCase<Params>() {

    protected abstract fun buildUseCaseSingle(params: Params): Single<T>

    override fun get(params: Params?): Single<T> =
        buildUseCaseSingle(params = requireParams(params))

    override fun execute(params: Params?): Single<T> =
        get(params).subscribeOn(executorThread)
            .observeOn(postExecutionThread)
}
