/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash.mvi

import kr.ohyung.core.mvi.ViewResult

sealed class SplashViewResult : ViewResult {
    object Loading : SplashViewResult()
    object Success : SplashViewResult()
    data class Error(val throwable: Throwable) : SplashViewResult()
}
