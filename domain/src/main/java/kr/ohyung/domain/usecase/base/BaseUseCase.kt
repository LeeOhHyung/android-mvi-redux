/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.domain.usecase.base

import kr.ohyung.domain.UseCase
import kr.ohyung.domain.exception.NoParamsException

abstract class ParameterizedUseCase<in Params> : UseCase {
    abstract fun execute(params: Params?): Any
}

abstract class NoParamsUseCase : UseCase {
    abstract fun execute(): Any
}

// if params is null, throw exception.
// note that, params cannot be null.
fun <T: Any> ParameterizedUseCase<*>.requireParams(value: T?): T {
    if(value == null) {
        throw NoParamsException()
    } else {
        return value
    }
}
