/*
 * Created by Lee Oh Hyung on 2020/09/17.
 */
package kr.ohyung.core.mvi

import androidx.lifecycle.LiveData

interface StateStore<S: ViewState, R: ViewResult> {
    /**
     * currentState + Result = newState
     * note that Action is not Result itself.
     * @Result is literally outcome of Actions.
     */
    val reducer: ViewStateReducer<S, R>
    val currentState: LiveData<S>
}
