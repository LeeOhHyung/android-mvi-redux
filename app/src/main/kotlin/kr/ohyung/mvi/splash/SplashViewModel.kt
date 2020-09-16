/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.BackpressureStrategy
import io.reactivex.functions.BiFunction
import kr.ohyung.core.android.BaseViewModel

class SplashViewModel @ViewModelInject constructor(
    splashProcessor: SplashActionProcessor,
) : BaseViewModel<SplashIntent, SplashAction, SplashViewState, SplashResult>() {

    override val reducer = BiFunction { oldState: SplashViewState, result: SplashResult ->
        when(result) {
            SplashResult.Loading -> oldState.copy(isLoading = true)
            SplashResult.Success -> oldState.copy(isLoading = false, error = null)
            is SplashResult.Error -> oldState.copy(isLoading = false, error = result.throwable)
        }
    }

    override val currentState: LiveData<SplashViewState> =
        LiveDataReactiveStreams.fromPublisher(
            intentsSubject
                .map(::intentToAction)
                .compose(splashProcessor.actionToResult())
                .scan(SplashViewState.idle(), reducer)
                .distinctUntilChanged()
                .replay(1)
                .autoConnect(0)
                .toFlowable(BackpressureStrategy.BUFFER)
        )

    override fun intentToAction(intent: SplashIntent): SplashAction =
        when(intent) {
            is SplashIntent.InitialIntent -> SplashAction.NavigateToHomeAction(intent.duration)
        }
}
