/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash

import kr.ohyung.core.mvi.ViewIntent

sealed class SplashIntent : ViewIntent {
    data class InitialIntent(val duration: Long) : SplashIntent()
}
