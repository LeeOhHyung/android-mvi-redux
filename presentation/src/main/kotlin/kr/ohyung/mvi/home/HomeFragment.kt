/*
 * Created by Lee Oh Hyung on 2020/10/15.
 */
package kr.ohyung.mvi.home

import android.content.pm.PackageManager
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kr.ohyung.core.android.MviFragment
import kr.ohyung.mvi.R
import kr.ohyung.mvi.databinding.FragmentHomeBinding
import kr.ohyung.mvi.home.mvi.HomeViewIntent
import kr.ohyung.mvi.home.mvi.HomeViewState
import kr.ohyung.mvi.utility.extension.*

@AndroidEntryPoint
class HomeFragment : MviFragment<FragmentHomeBinding,
        HomeViewIntent, HomeViewState>(R.layout.fragment_home) {

    private val getLocationSubject = PublishSubject.create<HomeViewIntent.InitHomeScreen>()
    private val homeViewModel by navGraphViewModels<HomeViewModel>(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }
    private val homeAdapter by lazy {
        HomeAdapter()
    }
    private val gridLayoutManager by lazy {
        GridLayoutManager(context, SPAN_COUNT_DEFAULT)
            .apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int =
                        when (homeAdapter.getItemViewType(position)) {
                            HomeAdapter.ViewType.PHOTO.index -> SPAN_SIZE_PHOTO
                            else -> SPAN_COUNT_DEFAULT
                        }
                }
            }
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
        if (requestCode == REQUEST_CODE_PERMISSION && grantResults.isNotEmpty()) {
            if (grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                doOnLocationPermissions()
            }
        }
    }

    override fun initView() {
        binding.recyclerView.adapter = homeAdapter
        binding.recyclerView.layoutManager = gridLayoutManager
        binding.recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                val layoutParams = view.layoutParams as GridLayoutManager.LayoutParams
                val position: Int = parent.getChildAdapterPosition(view)
                val viewType: Int? = parent.adapter?.getItemViewType(position)

                if (viewType == HomeAdapter.ViewType.PHOTO.index) {
                    with(outRect) {
                        bottom = 16.dpToPx()
                        left = if (layoutParams.spanIndex == 0) 16.dpToPx() else 8.dpToPx()
                        right = if (layoutParams.spanIndex == layoutParams.spanSize - 1) 8.dpToPx() else 16.dpToPx()
                    }
                }
            }
        })

        binding.swipeLayout.setOnRefreshListener {
            binding.swipeLayout.isRefreshing = true
            homeViewModel.retry()
        }
    }

    override fun render(state: HomeViewState) = with(state) {
        if(photos.isNotEmpty()) {
            homeAdapter.submitList(forecast, photos)
            binding.swipeLayout.isRefreshing = false
        }
        if (error != null)
            toast(error.message.toString())
    }

    override val intents: Observable<HomeViewIntent>
        get() = Observable.merge(
            Observable.just(HomeViewIntent.Noting),
            getLocationSubject
        )

    override fun subscribeIntents() = homeViewModel.subscribeIntents(intents)

    private fun doOnLocationPermissions() {
        if (isPermissionGranted()) {
            getLocationSubject.onNext(HomeViewIntent.InitHomeScreen)
        } else {
            requestPermissions()
        }
    }

    companion object {
        private const val SPAN_COUNT_DEFAULT = 2
        private const val SPAN_SIZE_PHOTO = 1
    }
}
