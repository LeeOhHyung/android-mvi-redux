/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash.mvi

import kr.ohyung.core.mvi.ViewAction

sealed class SplashViewAction : ViewAction {
    data class FetchImageFromApi(val query: String) : SplashViewAction()
    data class Loading(val duration: Long) : SplashViewAction()
}
