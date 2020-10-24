/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.core.android

import io.reactivex.Observable
import kr.ohyung.core.mvi.ViewIntent
import kr.ohyung.core.mvi.ViewState
import kr.ohyung.core.mvi.ViewRenderer

interface MviView<I: ViewIntent, S: ViewState> : ViewRenderer<S> {
    fun subscribeIntents()
    val intents: Observable<I>
}
