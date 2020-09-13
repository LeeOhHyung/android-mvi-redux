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
import androidx.lifecycle.Observer
import kr.ohyung.core.mvi.Intent
import kr.ohyung.core.mvi.UiState

abstract class BaseFragment<V: ViewDataBinding, I: Intent, S: UiState>(
    @LayoutRes private val layoutId: Int
) : Fragment(), BaseView<I, S> {

    protected lateinit var binding: V

    abstract val viewModel: BaseViewModel<I, *, S, *>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeIntent()
    }

    override fun subscribeIntent() {
        viewModel.subscribeIntents(mergeIntents())
    }
}
