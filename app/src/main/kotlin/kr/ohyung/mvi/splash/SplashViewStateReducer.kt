/*
 * Created by Lee Oh Hyung on 2020/09/17.
 */
package kr.ohyung.mvi.splash

import io.reactivex.functions.BiFunction
import kr.ohyung.core.mvi.ViewStateReducer
import kr.ohyung.mvi.splash.mvi.SplashResult
import kr.ohyung.mvi.splash.mvi.SplashViewState
import javax.inject.Inject

class SplashViewStateReducer @Inject constructor(): ViewStateReducer<SplashViewState, SplashResult> {
    override fun reduce() = BiFunction { oldState: SplashViewState, result: SplashResult ->
        when(result) {
            SplashResult.Loading -> oldState.copy(isLoading = true)
            SplashResult.Success -> oldState.copy(isLoading = false, error = null)
            is SplashResult.Error -> oldState.copy(isLoading = false, error = result.throwable)
        }
    }
}
