/*
 * Created by Lee Oh Hyung on 2020/09/17.
 */
package kr.ohyung.mvi.splash.mvi

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.BackpressureStrategy
import kr.ohyung.core.mvi.StateMachine
import kr.ohyung.core.mvi.ViewStateReducer
import kr.ohyung.mvi.splash.processor.SplashActionProcessor
import kr.ohyung.mvi.splash.processor.SplashIntentProcessor
import javax.inject.Inject

class SplashStateMachine @Inject constructor(
    intentProcessor: SplashIntentProcessor,
    actionProcessor: SplashActionProcessor,
    override val reducer: ViewStateReducer<SplashViewState, SplashViewResult>
) : StateMachine<SplashViewIntent, SplashViewAction, SplashViewState, SplashViewResult>(
    intentProcessor = intentProcessor
) {
    override val currentState: LiveData<SplashViewState> =
        LiveDataReactiveStreams.fromPublisher(
            intentProcessor.intentsSubject
                .map { intent -> intentProcessor.toAction(intent) }
                .compose(actionProcessor.compose())
                .scan(SplashViewState.idle(), reducer.reduce())
                .distinctUntilChanged()
                .replay(1)
                .autoConnect(0)
                .toFlowable(BackpressureStrategy.BUFFER)
        )
}
