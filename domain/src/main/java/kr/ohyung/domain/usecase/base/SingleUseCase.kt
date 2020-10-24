/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.domain.usecase.base

import io.reactivex.Scheduler
import io.reactivex.Single

abstract class SingleUseCase<T>(
    private val executorThread: Scheduler,
    private val postExecutionThread: Scheduler
) : NoParamsUseCase() {

    protected abstract fun buildUseCaseSingle(): Single<T>

    override fun get(): Single<T> = buildUseCaseSingle()

    override fun execute(): Single<T> =
        buildUseCaseSingle()
            .subscribeOn(executorThread)
            .observeOn(postExecutionThread)
}
