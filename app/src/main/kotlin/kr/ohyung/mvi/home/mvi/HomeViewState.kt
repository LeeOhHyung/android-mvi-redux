/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.mvi.home.mvi

import kr.ohyung.core.mvi.ViewState

data class HomeViewState(
    val isLoading: Boolean,
    val currentAddress: String,
    val error: Throwable?
) : ViewState {
    companion object {
        fun idle() = HomeViewState(
            isLoading = true,
            currentAddress = "현재위치를 가져오는 중 입니다...",
            error = null
        )
    }
}
