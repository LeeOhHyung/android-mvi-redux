/*
 * Created by Lee Oh Hyung on 2020/10/18.
 */
package kr.ohyung.mvi.utility

import android.content.Context
import androidx.annotation.StringRes

fun Context.getString(@StringRes resId: Int): String = getString(resId)
fun Context.getString(@StringRes resId: Int, vararg args: Any): String = getString(resId, args)
