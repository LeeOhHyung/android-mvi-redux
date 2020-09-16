/*
 * Created by Lee Oh Hyung on 2020/09/17.
 */
package kr.ohyung.mvi.splash

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kr.ohyung.core.mvi.IntentProcessor

class SplashIntentProcessor : IntentProcessor<SplashIntent, SplashAction> {
    override val intentsSubject = PublishSubject.create<SplashIntent>()
    override fun subscribeIntents(intents: Observable<SplashIntent>) = intents.subscribe(intentsSubject)
    override fun intentToAction(intent: SplashIntent) =
        when(intent) {
            is SplashIntent.InitialIntent -> SplashAction.NavigateToHomeAction(intent.duration)
        }
}
