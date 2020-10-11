/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import kr.ohyung.core.android.BaseFragment
import kr.ohyung.mvi.R
import kr.ohyung.mvi.databinding.FragmentSplashBinding
import kr.ohyung.mvi.splash.mvi.SplashViewIntent
import kr.ohyung.mvi.splash.mvi.SplashViewState
import kr.ohyung.mvi.utility.*

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding,
        SplashViewIntent, SplashViewState>(R.layout.fragment_splash) {

    private val args by navArgs<SplashFragmentArgs>()
    private val splashViewModel by navGraphViewModels<SplashViewModel>(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().setTransparentStatusBar()
        splashViewModel.viewState.observe(viewLifecycleOwner, Observer(::render))
    }

    override fun render(state: SplashViewState) {
        if(state.isLoading) {
            binding.progressBar.isVisible = true
        }
        if(state.imageUrl.isNullOrEmpty().not()) {
            binding.ivSplashImage.load(state.imageUrl) {
                centerCrop()
                setOnLoadFailedListener { binding.progressBar.isVisible = false }
                setOnResourceReadyListener { binding.progressBar.isVisible = false }
            }
        }
        if(state.timerEnd) {
            // Home 화면으로 이동
            //findNavController().navigate()
        }
        if(state.error != null){
            toast(state.error.message.toString())
        }
    }

    override val intents: Observable<SplashViewIntent>
        get() = Observable.just(SplashViewIntent.InitialIntent(duration = args.duration, query = args.query))

    override fun subscribeIntents() = splashViewModel.subscribeIntents(intents)
}
