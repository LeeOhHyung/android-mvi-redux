/*
 * Created by Lee Oh Hyung on 2020/09/17.
 */
package kr.ohyung.mvi.splash.mvi

import kr.ohyung.core.mvi.StateMachine
import kr.ohyung.mvi.splash.processor.SplashActionProcessor
import kr.ohyung.mvi.splash.processor.SplashIntentProcessor
import javax.inject.Inject

class SplashStateMachine @Inject constructor(
    intentProcessor: SplashIntentProcessor,
    actionProcessor: SplashActionProcessor,
    reducer: SplashViewStateReducer
) : StateMachine<SplashViewIntent, SplashViewAction, SplashViewState, SplashViewResult>(
    intentProcessor = intentProcessor,
    actionProcessor = actionProcessor,
    reducer = reducer,
    initialState = SplashViewState.idle()
)
