/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.mvi.home.mvi

import kr.ohyung.core.mvi.ViewResult
import kr.ohyung.domain.entity.Forecast
import kr.ohyung.domain.entity.LegalName

sealed class HomeViewResult : ViewResult {
    sealed class GetLocationAndPhotosResult : HomeViewResult() {
        object Loading : GetLocationAndPhotosResult()
        data class Success(val forecast: Forecast) : GetLocationAndPhotosResult()
        data class Error(val throwable: Throwable?) : GetLocationAndPhotosResult()
    }
}
