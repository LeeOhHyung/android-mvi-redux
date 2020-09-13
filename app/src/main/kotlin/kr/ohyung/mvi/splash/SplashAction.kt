/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash

import kr.ohyung.core.mvi.Action

sealed class SplashAction : Action {
    data class NavigateToHomeAction(val duration: Long) : SplashAction()
}
