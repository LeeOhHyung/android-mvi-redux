/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash.mvi

import kr.ohyung.core.mvi.ViewState

data class SplashViewState(
    val imageUrl: String?,
    val isLoading: Boolean,
    val timerEnd: Boolean,
    val error: Throwable?
) : ViewState {
    companion object {
        fun idle() =
            SplashViewState(
                imageUrl = null,
                isLoading = true,
                timerEnd = false,
                error = null
            )
    }
}
