/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.mvi.home.processor

import io.reactivex.ObservableTransformer
import kr.ohyung.core.mvi.ActionProcessor
import kr.ohyung.domain.executor.ExecutorProvider
import kr.ohyung.domain.usecase.GetCurrentLegalNameUseCase
import kr.ohyung.mvi.home.mvi.HomeViewAction
import kr.ohyung.mvi.home.mvi.HomeViewResult
import javax.inject.Inject

class HomeActionProcessor @Inject constructor(
    private val getCurrentLegalNameUseCase: GetCurrentLegalNameUseCase,
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
            actions.flatMapSingle {
                getCurrentLegalNameUseCase.execute()
                    .subscribeOn(executorProvider.io())
                    .observeOn(executorProvider.mainThread())
                    .cast(HomeViewResult::class.java)
            }
        }
}
