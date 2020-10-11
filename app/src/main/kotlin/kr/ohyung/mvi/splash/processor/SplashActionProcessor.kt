/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash.processor

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import kr.ohyung.core.mvi.ActionProcessor
import kr.ohyung.domain.usecase.GetRandomPhotoUseCase
import kr.ohyung.mvi.splash.mvi.SplashViewAction
import kr.ohyung.mvi.splash.mvi.SplashViewResult
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashActionProcessor @Inject constructor(
    private val getRandomPhotoUseCase: GetRandomPhotoUseCase
) : ActionProcessor<SplashViewAction, SplashViewResult> {

    override fun compose() =
        ObservableTransformer<SplashViewAction, SplashViewResult> { action ->
            action.publish { selector ->
                selector
                    .ofType(SplashViewAction.Loading::class.java)
                    .compose(fetchRandomImage)
            }
        }

    private val fetchRandomImage =
        ObservableTransformer<SplashViewAction, SplashViewResult> { actions ->
            actions.flatMap { action ->
                if(action is SplashViewAction.Loading) {
                    getRandomPhotoUseCase.execute(params = action.query)
                        .map { response -> SplashViewResult.Success(imageUrl = response.regularImageUrl) }
                        .toObservable()
                        .cast(SplashViewResult::class.java)
                        .onErrorReturn { throwable -> SplashViewResult.Error(throwable) }
                        .startWith(SplashViewResult.Loading)
                        .concatWith(
                            Single.timer(action.duration, TimeUnit.MILLISECONDS)
                                .map { SplashViewResult.Success(timerEnd = true) }
                        )
                } else {
                    Observable.error(IllegalStateException("this transformer must be handled about loading state"))
                }
            }
        }
}
