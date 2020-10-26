/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.mvi.home.processor

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import kr.ohyung.core.mvi.ActionProcessor
import kr.ohyung.domain.entity.Forecast
import kr.ohyung.domain.entity.PhotoSummary
import kr.ohyung.domain.executor.ExecutorProvider
import kr.ohyung.domain.usecase.GetCurrentLocationForecastUseCase
import kr.ohyung.domain.usecase.SearchParams
import kr.ohyung.domain.usecase.SearchPhotoUseCase
import kr.ohyung.mvi.home.mvi.HomeViewAction
import kr.ohyung.mvi.home.mvi.HomeViewResult
import javax.inject.Inject

class HomeActionProcessor @Inject constructor(
    private val getCurrentLocationForecastUseCase: GetCurrentLocationForecastUseCase,
    private val searchPhotoUseCase: SearchPhotoUseCase,
    private val executorProvider: ExecutorProvider
) : ActionProcessor<HomeViewAction, HomeViewResult> {

    override fun compose(): ObservableTransformer<HomeViewAction, HomeViewResult> =
        ObservableTransformer { actions ->
            actions.publish { selector ->
                Observable.merge(
                    selector.ofType(HomeViewAction.GetLocationAndPhotos::class.java)
                        .compose(getLocationAndWeatherPhotos),
                    selector.ofType(HomeViewAction.PhotoLoadMore::class.java)
                        .compose(getPagedPhotos)
                )
            }
        }

    private val getLocationAndWeatherPhotos =
        ObservableTransformer<HomeViewAction.GetLocationAndPhotos, HomeViewResult> { actions ->
            actions.flatMap {
                getCurrentLocationForecastUseCase.get()
                    .flatMapSingle { forecast ->
                        Single.zip(
                            Single.just(forecast),
                            searchPhotoUseCase.get(params = SearchParams(query = forecast.weather.name))
                                .subscribeOn(executorProvider.io()),
                            BiFunction { _: Forecast, photos: List<PhotoSummary> ->
                                HomeViewResult.GetLocationAndPhotosResult.Success(forecast, photos)
                            }
                        )
                    }
                    .toObservable()
                    .cast(HomeViewResult::class.java)
                    .onErrorReturn { HomeViewResult.GetLocationAndPhotosResult.Error(it) }
                    .subscribeOn(executorProvider.io())
                    .observeOn(executorProvider.mainThread())
                    .startWith(HomeViewResult.GetLocationAndPhotosResult.Loading)
            }
        }

    private val getPagedPhotos =
        ObservableTransformer<HomeViewAction.PhotoLoadMore, HomeViewResult> { actions ->
            actions.flatMap { action ->
                searchPhotoUseCase.execute(SearchParams(query = action.query, page = action.page))
                    .map { photos ->
                        HomeViewResult.SearchPagedPhotoResult.Success(photos)
                    }
                    .toObservable()
                    .cast(HomeViewResult::class.java)
                    .onErrorReturn { HomeViewResult.SearchPagedPhotoResult.Error(it) }
                    .startWith(HomeViewResult.SearchPagedPhotoResult.Loading)
            }
        }
}
