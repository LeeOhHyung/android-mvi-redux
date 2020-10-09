/*
 * Created by Lee Oh Hyung on 2020/10/09.
 */
package kr.ohyung.mvi.utility

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

internal fun ImageView.load(imageUrl: String?) = loadInternals(imageUrl)
internal fun ImageView.load(imageUrl: String?, @DrawableRes placeHolder: Int) = loadInternals(imageUrl, placeHolder)
internal fun ImageView.load(imageUrl: String?, centerCrop: Boolean) = loadInternals(imageUrl, centerCrop = centerCrop)
internal fun ImageView.load(imageUrl: String?, @DrawableRes placeHolder: Int, centerCrop: Boolean) = loadInternals(imageUrl, placeHolder, centerCrop)

private fun ImageView.loadInternals(
    imageUrl: String?,
    @DrawableRes placeHolder: Int = 0,
    centerCrop: Boolean = false
) = Glide.with(this)
    .load(imageUrl)
    .placeholder(placeHolder)
    .also { if(centerCrop) it.centerCrop() }
    .into(this)
