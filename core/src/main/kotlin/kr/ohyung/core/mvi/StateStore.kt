package kr.ohyung.core.mvi

import androidx.lifecycle.LiveData
import io.reactivex.functions.BiFunction

interface StateStore<S: UiState, R: Result> {

    /**
     * currentState + Result = newState
     * note that Action is not Result itself.
     * @Result is literally outcome of Actions.
     */
    val reducer: BiFunction<S, R, S>

    val currentState: LiveData<S>
}
