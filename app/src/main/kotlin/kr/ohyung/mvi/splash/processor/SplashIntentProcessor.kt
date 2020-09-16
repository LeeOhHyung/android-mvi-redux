/*
 * Created by Lee Oh Hyung on 2020/09/17.
 */
package kr.ohyung.mvi.splash.processor

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kr.ohyung.core.mvi.IntentProcessor
import kr.ohyung.mvi.splash.mvi.SplashAction
import kr.ohyung.mvi.splash.mvi.SplashIntent
import javax.inject.Inject

class SplashIntentProcessor @Inject constructor() : IntentProcessor<SplashIntent, SplashAction> {
    override val intentsSubject = PublishSubject.create<SplashIntent>()
    override fun subscribeIntents(intents: Observable<SplashIntent>) = intents.subscribe(intentsSubject)
    override fun intentToAction(intent: SplashIntent) =
        when(intent) {
            is SplashIntent.InitialIntent -> SplashAction.NavigateToHomeAction(intent.duration)
        }
}
