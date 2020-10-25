/*
 * Created by Lee Oh Hyung on 2020/10/18.
 */
package kr.ohyung.mvi.home.mvi

import io.reactivex.functions.BiFunction
import kr.ohyung.core.mvi.ViewStateReducer
import javax.inject.Inject

class HomeViewStateReducer @Inject constructor() : ViewStateReducer<HomeViewState, HomeViewResult> {

    override fun reduce(): BiFunction<HomeViewState, HomeViewResult, HomeViewState> = BiFunction { oldState: HomeViewState, result: HomeViewResult ->
        when(result) {
            is HomeViewResult.GetLocationAndPhotosResult -> {
                when(result) {
                    HomeViewResult.GetLocationAndPhotosResult.Loading -> oldState.copy(isLoading = true)
                    is HomeViewResult.GetLocationAndPhotosResult.Success ->
                        oldState.copy(
                            isLoading = false,
                            forecast = result.forecast,
                            photos = result.photos
                        )
                    is HomeViewResult.GetLocationAndPhotosResult.Error -> oldState.copy(isLoading = false, error = result.throwable)
                }
            }
            is HomeViewResult.SearchPagedPhotoResult -> {
                when(result) {
                    HomeViewResult.SearchPagedPhotoResult.Loading -> oldState.copy(isLoading = true)
                    is HomeViewResult.SearchPagedPhotoResult.Success -> oldState.copy(isLoading = false, photos = oldState.photos)
                    is HomeViewResult.SearchPagedPhotoResult.Error -> oldState.copy(isLoading = false, error = result.throwable)
                }
            }
        }
    }
}
