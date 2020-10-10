/*
 * Created by Lee Oh Hyung on 2020/09/17.
 */
package kr.ohyung.core.mvi

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
                .compose(actionProcessor.compose())
                .scan(initialState, reducer.reduce())
                .distinctUntilChanged()
                .replay(1)
                .autoConnect(1)
                .toFlowable(BackpressureStrategy.BUFFER)
        )

    fun subscribeIntents(intents: Observable<I>) = intents
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(intentProcessor.intentsSubject)

    fun processIntent(intent: I) = intentProcessor.intentsSubject.onNext(intent)
}
