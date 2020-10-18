/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.mvi.home.mvi

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.BackpressureStrategy
import kr.ohyung.core.mvi.StateMachine
import kr.ohyung.core.mvi.ViewStateReducer
import kr.ohyung.mvi.home.processor.HomeActionProcessor
import kr.ohyung.mvi.home.processor.HomeIntentProcessor
import javax.inject.Inject

class HomeStateMachine @Inject constructor(
    intentProcessor: HomeIntentProcessor,
    actionProcessor: HomeActionProcessor,
    override val reducer: ViewStateReducer<HomeViewState, HomeViewResult>
) : StateMachine<HomeViewIntent, HomeViewAction, HomeViewState, HomeViewResult>(
    intentProcessor = intentProcessor
) {
    override val currentState: LiveData<HomeViewState> =
        LiveDataReactiveStreams.fromPublisher(
            intentProcessor.intentsSubject
                .map { intent -> intentProcessor.toAction(intent) }
                .compose(actionProcessor.compose())
                .scan(HomeViewState.idle(), reducer.reduce())
                .distinctUntilChanged()
                .replay(1)
                .autoConnect(0)
                .toFlowable(BackpressureStrategy.BUFFER)
        )
}
