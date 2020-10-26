/*
 * Created by Lee Oh Hyung on 2020/10/24.
 */
package kr.ohyung.mvi.utility.extension

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

fun Int.dpToPx() = (this * Resources.getSystem().displayMetrics.density).toInt()
fun Int.dpToPx(context: Context, dp: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)
