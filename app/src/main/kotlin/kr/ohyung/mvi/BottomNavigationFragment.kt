/*
 * Created by Lee Oh Hyung on 2020/10/14.
 */
package kr.ohyung.mvi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import kr.ohyung.core.android.BaseFragment
import kr.ohyung.mvi.databinding.FragmentBottomNavigationBinding
import kr.ohyung.mvi.utility.setWhiteStatusBar

class BottomNavigationFragment :
    BaseFragment<FragmentBottomNavigationBinding>(R.layout.fragment_bottom_navigation) {

    private var childNavController: NavController? = null
    private var bottomNavigationHostFragment: NavHostFragment? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        initBottomNavHostFragment()
        setupNavigationItemSelectedListener()
        requireActivity().setWhiteStatusBar()
        return binding.root
    }

    private fun initBottomNavHostFragment() {
        bottomNavigationHostFragment = childFragmentManager.findFragmentById(R.id.bottom_navigation_host_fragment) as? NavHostFragment
        childNavController = bottomNavigationHostFragment?.navController
        childNavController?.setGraph(R.navigation.nav_graph_bottom_navigation)
        childNavController?.navigate(R.id.fragment_home)
    }

    private fun setupNavigationItemSelectedListener() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val destinationId = when(item.itemId) {
                R.id.menu_home -> R.id.fragment_home
                R.id.menu_bookmark -> R.id.fragment_bookmark
                R.id.menu_settings -> R.id.fragment_settings
                else -> throw IllegalStateException("invalid fragment id of bottom navigation")
            }
            childNavController?.navigate(destinationId)
            return@setOnNavigationItemSelectedListener true
        }
    }
}
