/*
 * Created by Lee Oh Hyung on 2020/09/17.
 */
package kr.ohyung.core.mvi

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

abstract class StateMachine<I: ViewIntent, A: ViewAction, S: ViewState, R: ViewResult>(
    private val intentProcessor: IntentProcessor<I, A>,
    actionProcessor: ActionProcessor<A, R>,
    final override val reducer: ViewStateReducer<S, R>,
    initialState: S
): StateStore<S, R> {

    override val currentState: LiveData<S> =
        LiveDataReactiveStreams.fromPublisher(
            intentProcessor.intentsSubject
                .map { intent -> intentProcessor.intentToAction(intent) }
                .compose(actionProcessor.actionToResult())
                .scan(initialState, reducer.reduce())
                .distinctUntilChanged()
                .replay(1)
                .autoConnect()
                .toFlowable(BackpressureStrategy.BUFFER)
        )

    fun subscribeIntents(intents: Observable<I>) = intentProcessor.subscribeIntents(intents)
}
