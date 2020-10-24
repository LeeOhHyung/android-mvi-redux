/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.domain.usecase.base

import kr.ohyung.domain.UseCase
import kr.ohyung.domain.exception.NoParamsException

abstract class ParameterizedUseCase<in Params> : UseCase {
    abstract fun get(params: Params?): Any
    abstract fun execute(params: Params?): Any
}

abstract class NoParamsUseCase : UseCase {
    abstract fun get(): Any
    abstract fun execute(): Any
}

// ParameterizedUseCase 의 Params 로 전달된 인자가 null 이라면, throw exception.
fun <T: Any> ParameterizedUseCase<*>.requireParamsNonNull(value: T?): T {
    if(value == null) {
        throw NoParamsException()
    } else {
        return value
    }
}
