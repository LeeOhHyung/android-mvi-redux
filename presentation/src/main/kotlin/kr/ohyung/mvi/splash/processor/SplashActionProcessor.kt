/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash.processor

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import kr.ohyung.core.mvi.ActionProcessor
import kr.ohyung.domain.executor.ExecutorProvider
import kr.ohyung.domain.usecase.GetRandomPhotoUseCase
import kr.ohyung.mvi.splash.mvi.SplashViewAction
import kr.ohyung.mvi.splash.mvi.SplashViewResult
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashActionProcessor @Inject constructor(
    private val getRandomPhotoUseCase: GetRandomPhotoUseCase,
    private val executorProvider: ExecutorProvider
) : ActionProcessor<SplashViewAction, SplashViewResult> {

    override fun compose() =
        ObservableTransformer<SplashViewAction, SplashViewResult> { actions ->
            actions.publish { selector ->
                Observable.merge(
                    selector.ofType(SplashViewAction.FetchImageFromApi::class.java)
                        .compose(fetchRandomImage),
                    selector.ofType(SplashViewAction.Loading::class.java)
                        .compose(startLoadingTimer)
                )
            }
        }

    private val fetchRandomImage =
        ObservableTransformer<SplashViewAction.FetchImageFromApi, SplashViewResult> { actions ->
            actions.flatMap { action ->
                getRandomPhotoUseCase.execute(params = action.query)
                    .map { response -> SplashViewResult.Success(imageUrl = response.regularSizeImageUrl) }
                    .toObservable()
                    .cast(SplashViewResult::class.java)
                    .onErrorReturn { throwable -> SplashViewResult.Error(throwable) }
                    .subscribeOn(executorProvider.io())
                    .observeOn(executorProvider.mainThread())
                    .startWith(SplashViewResult.Loading)
            }
        }

    private val startLoadingTimer =
        ObservableTransformer<SplashViewAction.Loading, SplashViewResult> { actions ->
            actions.flatMapSingle { action ->
                Single.timer(action.duration, TimeUnit.MILLISECONDS)
                    .map { SplashViewResult.Success(timerEnd = true) }
                    .cast(SplashViewResult::class.java)
                    .onErrorReturn { throwable -> SplashViewResult.Error(throwable) }
                    .subscribeOn(executorProvider.io())
                    .observeOn(executorProvider.mainThread())
            }
        }
}
