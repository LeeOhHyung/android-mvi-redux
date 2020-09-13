/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash

import androidx.annotation.DrawableRes
import kr.ohyung.core.mvi.UiState

data class SplashUiState(
    @DrawableRes val imageResId: Int?,
    val imageUrl: String?,
    val isLoading: Boolean,
    val error: Throwable?
) : UiState {
    companion object {
        fun idle() =
            SplashUiState(
                imageResId = null,
                imageUrl = null,
                isLoading = true,
                error = null
            )
    }
}
