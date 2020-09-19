/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.domain.usecase.base

import io.reactivex.Scheduler
import kr.ohyung.domain.UseCase
import kr.ohyung.domain.exception.NoParamsException

abstract class BaseUseCase<in Params> : UseCase {
    abstract val executorThread: Scheduler
    abstract val postExecutionThread: Scheduler
    abstract fun execute(params: Params?): Any
}

// if params is null, throw exception.
// note that, params cannot be null.
fun <T: Any> BaseUseCase<*>.requireParams(value: T?): T {
    if(value == null) {
        throw NoParamsException()
    } else {
        return value
    }
}
