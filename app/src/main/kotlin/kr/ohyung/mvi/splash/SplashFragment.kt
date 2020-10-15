/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.mvi.splash

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kr.ohyung.core.android.BaseMviFragment
import kr.ohyung.core.mvi.ViewIntent
import kr.ohyung.core.mvi.toPublisher
import kr.ohyung.core.mvi.toPublisherWithEmit
import kr.ohyung.mvi.R
import kr.ohyung.mvi.databinding.FragmentSplashBinding
import kr.ohyung.mvi.splash.mvi.SplashViewIntent
import kr.ohyung.mvi.splash.mvi.SplashViewState
import kr.ohyung.mvi.utility.*

@AndroidEntryPoint
class SplashFragment : BaseMviFragment<FragmentSplashBinding,
        SplashViewIntent, SplashViewState>(R.layout.fragment_splash) {

    private val toHomeScreenSubject = PublishSubject.create<SplashViewIntent.ToHomeScreen>()
    private val initSubject = PublishSubject.create<SplashViewIntent.FetchImage>()

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
                setOnDrawableListener {
                    binding.progressBar.isVisible = false
                    toHomeScreenSubject.onNext(SplashViewIntent.ToHomeScreen(duration = args.duration))
                }
            }
        }
        if(state.timerEnd) {
            findNavController().navigate(R.id.to_bottom_navigation_fragment)
        }
        if(state.error != null){
            toast(state.error.message.toString())
        }
    }

    override val intents: Observable<SplashViewIntent>
        get() = Observable.merge(
            Observable.just(SplashViewIntent.FetchImage(query = args.query)),
            toHomeScreenSubject
        )

    override fun subscribeIntents() = splashViewModel.subscribeIntents(intents)
}
