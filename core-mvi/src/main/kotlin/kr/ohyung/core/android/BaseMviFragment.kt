/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.core.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import kr.ohyung.core.mvi.ViewIntent
import kr.ohyung.core.mvi.ViewState

abstract class BaseMviFragment<V: ViewDataBinding, I: ViewIntent,
    S: ViewState>(@LayoutRes layoutId: Int) : BaseFragment<V>(layoutId), BaseView<I, S> {

    override fun initView() { /* explicitly empty */ }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeIntents()
    }
}
