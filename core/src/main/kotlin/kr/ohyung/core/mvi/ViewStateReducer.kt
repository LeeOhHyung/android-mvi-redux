/*
 * Created by Lee Oh Hyung on 2020/09/17.
 */
package kr.ohyung.core.mvi

import io.reactivex.functions.BiFunction

interface ViewStateReducer<S: ViewState, R: ViewResult> {
    fun reduce(): BiFunction<S, R, S>
}
