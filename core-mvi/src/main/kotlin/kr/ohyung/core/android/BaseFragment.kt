/*
 * Created by Lee Oh Hyung on 2020/10/14.
 */
package kr.ohyung.core.android

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<V: ViewDataBinding>(
    @LayoutRes private val layoutId: Int
) : Fragment(layoutId) {

    protected lateinit var binding: V

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        Log.e("OnResume", "여기는 : ${javaClass.simpleName}")
    }
}
