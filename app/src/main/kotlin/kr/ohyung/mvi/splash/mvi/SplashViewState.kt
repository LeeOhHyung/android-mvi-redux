/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash.mvi

import androidx.annotation.DrawableRes
import kr.ohyung.core.mvi.ViewState

data class SplashViewState(
    @DrawableRes val imageResId: Int?,
    val imageUrl: String?,
    val isLoading: Boolean,
    val error: Throwable?
) : ViewState {
    companion object {
        fun idle() =
            SplashViewState(
                imageResId = null,
                imageUrl = null,
                isLoading = true,
                error = null
            )
    }
}
