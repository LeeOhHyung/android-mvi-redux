/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.core.android

import io.reactivex.Observable
import kr.ohyung.core.mvi.ViewIntent
import kr.ohyung.core.mvi.ViewState
import kr.ohyung.core.mvi.ViewRenderer

interface BaseView<I: ViewIntent, S: ViewState> : ViewRenderer<S> {
    fun initView()
    fun processIntents()
    fun mergeIntents(): Observable<I>
}
