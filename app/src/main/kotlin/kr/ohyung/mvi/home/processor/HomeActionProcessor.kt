/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.mvi.home.processor

import io.reactivex.ObservableTransformer
import kr.ohyung.core.mvi.ActionProcessor
import kr.ohyung.domain.executor.ExecutorProvider
import kr.ohyung.domain.usecase.GetCurrentLocationForecastUseCase
import kr.ohyung.mvi.home.mvi.HomeViewAction
import kr.ohyung.mvi.home.mvi.HomeViewResult
import javax.inject.Inject

class HomeActionProcessor @Inject constructor(
    private val getCurrentLocationForecastUseCase: GetCurrentLocationForecastUseCase,
    private val executorProvider: ExecutorProvider
) : ActionProcessor<HomeViewAction, HomeViewResult> {

    override fun compose(): ObservableTransformer<HomeViewAction, HomeViewResult> =
        ObservableTransformer { actions ->
            actions.publish { selector ->
                selector.ofType(HomeViewAction.GetLocationAndPhotos::class.java)
                    .compose(getLocationAndWeatherPhotos)
            }
        }

    private val getLocationAndWeatherPhotos =
        ObservableTransformer<HomeViewAction.GetLocationAndPhotos, HomeViewResult> { actions ->
            actions.flatMap {
                getCurrentLocationForecastUseCase.execute()
                    .map { forecast -> HomeViewResult.GetLocationAndPhotosResult.Success(forecast) }
                    .toObservable()
                    .cast(HomeViewResult::class.java)
                    .onErrorReturn { HomeViewResult.GetLocationAndPhotosResult.Error(it) }
                    .subscribeOn(executorProvider.io())
                    .observeOn(executorProvider.mainThread())
                    .startWith(HomeViewResult.GetLocationAndPhotosResult.Loading)
            }
        }
}
