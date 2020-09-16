/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash.mvi

import kr.ohyung.core.mvi.ViewAction

sealed class SplashAction : ViewAction {
    data class NavigateToHomeAction(val duration: Long) : SplashAction()
}
