/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.mvi.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kr.ohyung.core.android.MviViewModel
import kr.ohyung.domain.executor.ExecutorProvider
import kr.ohyung.mvi.home.mvi.*
import java.util.concurrent.TimeUnit

class HomeViewModel @ViewModelInject constructor(
    private val stateMachine: HomeStateMachine,
    private val executorProvider: ExecutorProvider
) : MviViewModel<HomeViewIntent, HomeViewAction, HomeViewState, HomeViewResult>(stateMachine) {

    override val viewState: LiveData<HomeViewState>
        get() = stateMachine.currentState

    private val pagedPhotoSubject = PublishSubject.create<HomeViewIntent.PhotoLoadMore>()
    private val retry = PublishSubject.create<HomeViewIntent.Retry>()

    init {
        subscribeIntents(Observable.merge(pagedPhotoSubject, retry))
    }

    fun getPagedPhotos(page: Int) =
        Observable.just(HomeViewIntent.PhotoLoadMore(query = viewState.value!!.forecast.weather.name, page = page))
            .throttleFirst(500L, TimeUnit.MILLISECONDS)
            .observeOn(executorProvider.mainThread())
            .subscribe(pagedPhotoSubject::onNext)
            .addDisposable()

    fun retry() = retry.onNext(HomeViewIntent.Retry)
}
