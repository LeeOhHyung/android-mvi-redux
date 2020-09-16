/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash

import kr.ohyung.core.mvi.ViewResult

sealed class SplashResult : ViewResult {
    object Loading : SplashResult()
    object Success : SplashResult()
    data class Error(val throwable: Throwable) : SplashResult()
}
