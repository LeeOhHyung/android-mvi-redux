/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash

import androidx.hilt.lifecycle.ViewModelInject
import kr.ohyung.core.android.BaseViewModel
import kr.ohyung.mvi.splash.mvi.*

class SplashViewModel @ViewModelInject constructor(
    splashStateMachine: SplashStateMachine
) : BaseViewModel<SplashViewIntent, SplashViewAction, SplashViewState, SplashViewResult>(splashStateMachine) {

    override val viewState = splashStateMachine.currentState
}
