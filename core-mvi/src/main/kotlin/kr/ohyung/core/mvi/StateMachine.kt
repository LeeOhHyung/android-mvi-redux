/*
 * Created by Lee Oh Hyung on 2020/09/17.
 */
package kr.ohyung.core.mvi

import io.reactivex.Observable

abstract class StateMachine<I: ViewIntent, A: ViewAction, S: ViewState, R: ViewResult>(
    val intentProcessor: IntentProcessor<I, A>
): StateStore<S, R> {

    fun subscribeIntents(intents: Observable<I>) = intents.subscribe(intentProcessor.intentsSubject)
    fun processIntent(intent: I) = intentProcessor.intentsSubject.onNext(intent)
}
