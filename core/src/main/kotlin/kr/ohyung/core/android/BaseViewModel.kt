package kr.ohyung.core.android

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import kr.ohyung.core.mvi.*

abstract class BaseViewModel<I: Intent, A: Action,
        S: UiState, R: Result> : ViewModel(), StateStore<S, R>, ActionDispatcher<I, A> {

    private val compositeDisposable = CompositeDisposable()
    override val intentsSubject = PublishSubject.create<I>()

    override fun subscribeIntents(intents: Observable<I>) = intents.subscribe(intentsSubject)

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun Disposable.addDisposable() = compositeDisposable.add(this)
}
