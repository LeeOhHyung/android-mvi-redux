/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.domain.usecase.base

import io.reactivex.Scheduler
import io.reactivex.Single

abstract class SingleUseCase<T, in Params>(
    override val executorThread: Scheduler,
    override val postExecutionThread: Scheduler
) : BaseUseCase<Params>() {

    protected abstract fun buildUseCaseSingle(params: Params): Single<T>

    override fun execute(params: Params?): Single<T> =
        buildUseCaseSingle(params = requireParams(params))
            .subscribeOn(executorThread)
            .observeOn(postExecutionThread)
}
