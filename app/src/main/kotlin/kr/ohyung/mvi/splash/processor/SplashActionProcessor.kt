/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash.processor

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kr.ohyung.core.mvi.ActionProcessor
import kr.ohyung.domain.entity.PhotoSummary
import kr.ohyung.domain.usecase.GetRandomPhotoUseCase
import kr.ohyung.mvi.splash.mvi.SplashViewAction
import kr.ohyung.mvi.splash.mvi.SplashViewResult
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashActionProcessor @Inject constructor(
    private val getRandomPhotoUseCase: GetRandomPhotoUseCase
) : ActionProcessor<SplashViewAction, SplashViewResult> {

    override fun actionToResult() =
        ObservableTransformer<SplashViewAction, SplashViewResult> { action ->
            action.publish { selector ->
                selector
                    .ofType(SplashViewAction.Loading::class.java)
                    .compose(navigateToHome)
            }
        }

    private val navigateToHome =
        ObservableTransformer<SplashViewAction, SplashViewResult> { actions ->
            actions.flatMap { action ->
                val duration = if(action is SplashViewAction.Loading) action.duration else 0L
                if(action is SplashViewAction.Loading) {
                    Single.zip(
                        getRandomPhotoUseCase.execute(params = action.query),
                        Single.timer(duration, TimeUnit.MILLISECONDS),
                        BiFunction { photo: PhotoSummary, _ ->
                            return@BiFunction SplashViewResult.Success(imageUrl = photo.thumbnail)
                        })
                        .cast(SplashViewResult::class.java)
                        .onErrorReturn { throwable -> SplashViewResult.Error(throwable) }
                        .toObservable()
                        .startWith(SplashViewResult.Loading)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                } else {
                    Observable.error(IllegalStateException("this transformer must be handled about loading state"))
                }
            }
        }
}
