/*
 * Created by Lee Oh Hyung on 2020/10/18.
 */
package kr.ohyung.mvi.utility.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import kr.ohyung.mvi.R
import kr.ohyung.mvi.utility.load

@BindingAdapter("setImageCenterCrop")
fun ImageView.setImage(imageUrl: String) = load(imageUrl) {
    placeholder(R.drawable.ic_weather)
    centerCrop()
}
