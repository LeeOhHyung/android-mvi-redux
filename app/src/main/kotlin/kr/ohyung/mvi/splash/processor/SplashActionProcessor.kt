/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash.processor

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.ohyung.core.mvi.ActionProcessor
import kr.ohyung.mvi.splash.mvi.SplashViewAction
import kr.ohyung.mvi.splash.mvi.SplashViewResult
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashActionProcessor @Inject constructor() : ActionProcessor<SplashViewAction, SplashViewResult> {

    override fun actionToResult() =
        ObservableTransformer<SplashViewAction, SplashViewResult> { action ->
            action.publish { selector ->
                selector
                    .ofType(SplashViewAction.NavigateToHomeAction::class.java)
                    .compose(navigateToHome)
            }
        }

    private val navigateToHome =
        ObservableTransformer<SplashViewAction, SplashViewResult> { actions ->
            actions.flatMap { action ->
                val duration = if(action is SplashViewAction.NavigateToHomeAction) action.duration else 0L
                Observable.timer(duration, TimeUnit.MILLISECONDS)
                    .map { _ -> SplashViewResult.Success }
                    .cast(SplashViewResult::class.java)
                    .startWith(SplashViewResult.Loading)
                    .onErrorReturn { throwable -> SplashViewResult.Error(throwable) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
}
