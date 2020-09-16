/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash

import androidx.hilt.lifecycle.ViewModelInject
import io.reactivex.Observable
import kr.ohyung.core.android.BaseViewModel
import kr.ohyung.mvi.splash.mvi.*

class SplashViewModel @ViewModelInject constructor(
    splashStateMachine: SplashStateMachine
) : BaseViewModel<SplashIntent, SplashAction, SplashViewState, SplashResult>(splashStateMachine) {

    override val viewState = splashStateMachine.currentState
}
