/*
 * Created by Lee Oh Hyung on 2020/10/15.
 */
package kr.ohyung.mvi.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import kr.ohyung.core.android.BaseFragment
import kr.ohyung.core.android.MviFragment
import kr.ohyung.mvi.R
import kr.ohyung.mvi.databinding.FragmentHomeBinding
import kr.ohyung.mvi.home.mvi.HomeViewIntent
import kr.ohyung.mvi.home.mvi.HomeViewState

@AndroidEntryPoint
class HomeFragment : MviFragment<FragmentHomeBinding,
    HomeViewIntent, HomeViewState>(R.layout.fragment_home) {

    private val homeViewModel by navGraphViewModels<HomeViewModel>(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.viewState.observe(viewLifecycleOwner, Observer(::render))
    }

    override fun render(state: HomeViewState) = with(state) {
        // do something with state.
    }

    override val intents: Observable<HomeViewIntent>
        get() = Observable.just(HomeViewIntent.InitHomeScreen)

    override fun subscribeIntents() = homeViewModel.subscribeIntents(intents)
}
