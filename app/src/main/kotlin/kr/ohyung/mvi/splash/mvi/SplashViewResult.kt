/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash.mvi

import kr.ohyung.core.mvi.ViewResult

sealed class SplashViewResult : ViewResult {
    object Loading : SplashViewResult()
    data class Success(val imageUrl: String? = null, val timerEnd: Boolean = false) : SplashViewResult()
    data class Error(val throwable: Throwable) : SplashViewResult()
}
