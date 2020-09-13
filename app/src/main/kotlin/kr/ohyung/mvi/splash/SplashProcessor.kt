/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash

import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.ohyung.core.mvi.Processor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashProcessor @Inject constructor() : Processor<SplashAction, SplashResult> {

    override val actionProcessor =
        ObservableTransformer<SplashAction, SplashResult> { action ->
            action.publish { selector ->
                selector.ofType(SplashAction.NavigateToHomeAction::class.java).compose(navigateToHome)
            }
        }

    private val navigateToHome =
        ObservableTransformer<SplashAction, SplashResult> { actions ->
            actions.flatMap { action ->
                val duration = if(action is SplashAction.NavigateToHomeAction) action.duration else 0L
                Observable.timer(duration, TimeUnit.MILLISECONDS)
                    .doOnNext { Log.d("Splash", "navigateToHomeProcessor") }
                    .map { _ -> SplashResult.Success }
                    .cast(SplashResult::class.java)
                    .startWith(SplashResult.Loading)
                    .onErrorReturn { throwable -> SplashResult.Error(throwable) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
}
