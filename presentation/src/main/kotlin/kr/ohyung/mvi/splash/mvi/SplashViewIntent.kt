/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash.mvi

import kr.ohyung.core.mvi.ViewIntent

sealed class SplashViewIntent : ViewIntent {
    data class FetchImage(val query: String) : SplashViewIntent()
    data class ToHomeScreen(val duration: Long) : SplashViewIntent()
}
