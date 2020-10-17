/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.core.android

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun Disposable.addDisposable() = compositeDisposable.add(this)
}
