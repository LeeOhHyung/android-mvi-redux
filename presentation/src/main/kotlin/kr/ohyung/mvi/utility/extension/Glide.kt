/*
 * Created by Lee Oh Hyung on 2020/10/09.
 */
package kr.ohyung.mvi.utility.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

internal fun ImageView.load(imageUrl: String?, action: RequestBuilder<Drawable>.() -> Unit) = loadInternals(imageUrl, action = action)
internal fun ImageView.load(imageUrl: String?) = loadInternals(imageUrl)
internal fun ImageView.load(imageUrl: String?, @DrawableRes placeHolder: Int) = loadInternals(imageUrl, placeHolder)
internal fun ImageView.load(imageUrl: String?, centerCrop: Boolean) = loadInternals(imageUrl, centerCrop = centerCrop)
internal fun ImageView.load(imageUrl: String?, @DrawableRes placeHolder: Int, centerCrop: Boolean) = loadInternals(imageUrl, placeHolder, centerCrop)

internal fun RequestBuilder<Drawable>.setOnDrawableListener(action: () -> Unit) =
    addListener(object : RequestListener<Drawable> {
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
            action.invoke()
            return false
        }

        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            action.invoke()
            return false
        }
    })

internal fun RequestBuilder<Drawable>.addOnLoadFailedListener(action: () -> Unit) =
    addListener(object : RequestListener<Drawable> {
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
            action.invoke()
            return false
        }
        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            return false
        }
    })

internal fun RequestBuilder<Drawable>.addOnResourceReadyListener(action: () -> Unit) =
    addListener(object : RequestListener<Drawable> {
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
            return false
        }
        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            action.invoke()
            return false
        }
    })

private fun ImageView.loadInternals(
    imageUrl: String?,
    @DrawableRes placeHolder: Int = 0,
    centerCrop: Boolean = false,
    action: RequestBuilder<Drawable>.() -> Unit = {}
) = Glide.with(this)
    .load(imageUrl)
    .apply(action)
    .placeholder(placeHolder)
    .also { if(centerCrop) it.centerCrop() }
    .into(this)
