package kr.ohyung.core.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kr.ohyung.core.mvi.*

abstract class BaseViewModel<I: ViewIntent, A: ViewAction, S: ViewState, R: ViewResult>(
    private val stateMachine: StateMachine<I, A, S, R>
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    abstract val viewState: LiveData<S>

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun Disposable.addDisposable() = compositeDisposable.add(this)
    fun subscribeIntents(intents: Observable<I>) = stateMachine.subscribeIntents(intents)
    fun processIntent(intent: I) = stateMachine.processIntent(intent)
}
