/*
 * Created by Lee Oh Hyung on 2020/10/14.
 */
package kr.ohyung.mvi.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import kr.ohyung.core.android.BaseFragment
import kr.ohyung.mvi.R
import kr.ohyung.mvi.databinding.FragmentBottomNavigationBinding

class BottomNavigationFragment :
    BaseFragment<FragmentBottomNavigationBinding>(R.layout.fragment_bottom_navigation) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        NavigationUI.setupWithNavController(binding.bottomNavigationView, findNavController())
        return binding.root
    }
}
