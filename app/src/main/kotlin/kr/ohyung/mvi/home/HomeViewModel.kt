/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.mvi.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import kr.ohyung.core.android.MviViewModel
import kr.ohyung.mvi.home.mvi.*

class HomeViewModel @ViewModelInject constructor(
    private val stateMachine: HomeStateMachine
) : MviViewModel<HomeViewIntent, HomeViewAction, HomeViewState, HomeViewResult>(stateMachine) {

    override val viewState: LiveData<HomeViewState>
        get() = stateMachine.currentState
}
