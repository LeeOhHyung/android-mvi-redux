/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.core.android

import io.reactivex.Observable
import kr.ohyung.core.mvi.Intent
import kr.ohyung.core.mvi.UiState
import kr.ohyung.core.mvi.ViewRenderer

interface BaseView<I: Intent, S: UiState> : ViewRenderer<S> {
    fun initView()
    fun mergeIntents(): Observable<I>
    fun subscribeIntent()
}
