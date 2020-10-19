/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import kr.ohyung.core.android.MviViewModel
import kr.ohyung.mvi.splash.mvi.*

class SplashViewModel @ViewModelInject constructor(
    private val splashStateMachine: SplashStateMachine
) : MviViewModel<SplashViewIntent, SplashViewAction, SplashViewState, SplashViewResult>(splashStateMachine) {

    override val viewState: LiveData<SplashViewState>
        get() = splashStateMachine.currentState
}
