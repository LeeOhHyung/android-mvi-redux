/*
 * Created by Lee Oh Hyung on 2020/10/11.
 */
package kr.ohyung.mvi.utility.extension

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View

fun Activity.setTransparentStatusBar() {
    window.run {
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        statusBarColor = Color.TRANSPARENT
    }
}

fun Activity.setWhiteStatusBar() {
    window.run {
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        statusBarColor = Color.WHITE
    }
}


fun Activity.setTransparentFullScreenStatusBar() {
    window.run {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        statusBarColor = Color.TRANSPARENT
    }
}
