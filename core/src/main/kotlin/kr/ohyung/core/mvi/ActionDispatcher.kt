/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.core.mvi

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

interface ActionDispatcher<I: Intent, A: Action> {
    val intentsSubject: PublishSubject<I>
    fun subscribeIntents(intents: Observable<I>)
    fun actionFromIntent(intent: I): A
}
