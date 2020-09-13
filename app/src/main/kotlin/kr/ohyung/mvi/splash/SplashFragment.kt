/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import kr.ohyung.core.android.BaseFragment
import kr.ohyung.mvi.R
import kr.ohyung.mvi.databinding.FragmentSplashBinding

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashIntent,
        SplashUiState>(R.layout.fragment_splash) {

    private val args by navArgs<SplashFragmentArgs>()
    private val splashViewModel by navGraphViewModels<SplashViewModel>(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        splashViewModel.currentState.observe(viewLifecycleOwner, Observer(::render))
    }

    override fun render(state: SplashUiState) {
        if(state.isLoading) {
            // TODO : show progress bar
        }
    }

    override fun subscribeIntent() = splashViewModel.subscribeIntents(mergeIntents())

    override fun mergeIntents(): Observable<SplashIntent> = Observable.just(SplashIntent.InitialIntent(args.duration))
}
