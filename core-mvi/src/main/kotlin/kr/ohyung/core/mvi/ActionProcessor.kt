/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.core.mvi

import io.reactivex.ObservableTransformer

interface ActionProcessor<A: ViewAction, R: ViewResult> {
    fun compose(): ObservableTransformer<A, R>
}
