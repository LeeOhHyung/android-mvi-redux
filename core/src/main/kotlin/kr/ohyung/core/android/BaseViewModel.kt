package kr.ohyung.core.android

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import kr.ohyung.core.mvi.*

abstract class BaseViewModel<I: ViewIntent, A: ViewAction,
        S: ViewState, R: ViewResult> : ViewModel(), StateStore<S, R> {

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun Disposable.addDisposable() = compositeDisposable.add(this)
}
