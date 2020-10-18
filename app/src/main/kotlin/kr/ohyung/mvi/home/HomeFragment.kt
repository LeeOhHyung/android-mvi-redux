/*
 * Created by Lee Oh Hyung on 2020/10/15.
 */
package kr.ohyung.mvi.home

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kr.ohyung.core.android.MviFragment
import kr.ohyung.mvi.R
import kr.ohyung.mvi.databinding.FragmentHomeBinding
import kr.ohyung.mvi.home.mvi.HomeViewIntent
import kr.ohyung.mvi.home.mvi.HomeViewState
import kr.ohyung.mvi.utility.REQUEST_CODE_PERMISSION
import kr.ohyung.mvi.utility.isPermissionGranted
import kr.ohyung.mvi.utility.requestPermissions
import kr.ohyung.mvi.utility.toast

@AndroidEntryPoint
class HomeFragment : MviFragment<FragmentHomeBinding,
    HomeViewIntent, HomeViewState>(R.layout.fragment_home) {

    private val getLocationSubject = PublishSubject.create<HomeViewIntent.InitHomeScreen>()
    private val homeViewModel by navGraphViewModels<HomeViewModel>(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = homeViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doOnLocationPermissions()
        homeViewModel.viewState.observe(viewLifecycleOwner, Observer(::render))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_CODE_PERMISSION && grantResults.isNotEmpty()) {
            if(grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                doOnLocationPermissions()
            }
        }
    }

    override fun render(state: HomeViewState) = with(state) {
        if(error != null)
            toast(error.message.toString())
    }

    override val intents: Observable<HomeViewIntent>
        get() = Observable.merge(
            Observable.just(HomeViewIntent.Noting),
            getLocationSubject
        )

    override fun subscribeIntents() = homeViewModel.subscribeIntents(intents)

    private fun doOnLocationPermissions() {
        if(isPermissionGranted()) {
            getLocationSubject.onNext(HomeViewIntent.InitHomeScreen)
        } else {
            requestPermissions()
        }
    }
}
