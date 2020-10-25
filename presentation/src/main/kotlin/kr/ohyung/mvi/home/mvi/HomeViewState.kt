/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.mvi.home.mvi

import kr.ohyung.core.mvi.ViewState
import kr.ohyung.domain.entity.Forecast
import kr.ohyung.domain.entity.LegalName
import kr.ohyung.domain.entity.PhotoSummary
import kr.ohyung.domain.entity.Weather

data class HomeViewState(
    val isLoading: Boolean,
    val isRefresh: Boolean,
    val forecast: Forecast,
    val photos: List<PhotoSummary>,
    val error: Throwable?
) : ViewState {
    companion object {
        fun idle() = HomeViewState(
            isLoading = true,
            isRefresh = false,
            forecast = Forecast(
                legalName = LegalName(
                    latitude = 0.0,
                    longitude = 0.0,
                    countryCode = "",
                    city = "현재위치를 가져오는 중 입니다..."
                ),
                weather = Weather(
                    latitude = 0.0,
                    longitude = 0.0,
                    name = "",
                    description = "",
                    icon = "",
                    temp = 0.0
                )
            ),
            photos = listOf(),
            error = null
        )
    }
}
