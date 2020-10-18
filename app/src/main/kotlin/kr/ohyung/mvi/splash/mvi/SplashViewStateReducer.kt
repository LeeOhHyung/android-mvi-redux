/*
 * Created by Lee Oh Hyung on 2020/09/17.
 */
package kr.ohyung.mvi.splash.mvi

import io.reactivex.functions.BiFunction
import kr.ohyung.core.mvi.ViewStateReducer
import javax.inject.Inject

class SplashViewStateReducer @Inject constructor() : ViewStateReducer<SplashViewState, SplashViewResult> {
    override fun reduce() = BiFunction { oldState: SplashViewState, result: SplashViewResult ->
        when(result) {
            SplashViewResult.Loading -> oldState.copy(isLoading = true)
            is SplashViewResult.Success -> {
                if(result.imageUrl != null) {
                    oldState.copy(isLoading = false, timerEnd = result.timerEnd, imageUrl = result.imageUrl)
                } else {
                    oldState.copy(isLoading = false, timerEnd = result.timerEnd, imageUrl = null)
                }
            }
            is SplashViewResult.Error -> oldState.copy(isLoading = false, error = result.throwable)
        }
    }
}
