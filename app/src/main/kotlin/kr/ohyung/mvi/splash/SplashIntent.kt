/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash

import kr.ohyung.core.mvi.Intent

sealed class SplashIntent : Intent {
    data class InitialIntent(val duration: Long) : SplashIntent()
}
