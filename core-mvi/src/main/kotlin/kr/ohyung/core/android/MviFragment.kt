/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.core.android

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import kr.ohyung.core.mvi.ViewIntent
import kr.ohyung.core.mvi.ViewState

abstract class MviFragment<V: ViewDataBinding, I: ViewIntent,
    S: ViewState>(@LayoutRes layoutId: Int) : BaseFragment<V>(layoutId), MviView<I, S> {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeIntents()
    }
}
