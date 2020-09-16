/*
 * Created by Lee Oh Hyung on 2020/09/17.
 */
package kr.ohyung.mvi.splash.processor

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kr.ohyung.core.mvi.IntentProcessor
import kr.ohyung.mvi.splash.mvi.SplashViewAction
import kr.ohyung.mvi.splash.mvi.SplashViewIntent
import javax.inject.Inject

class SplashIntentProcessor @Inject constructor() : IntentProcessor<SplashViewIntent, SplashViewAction> {
    override val intentsSubject = PublishSubject.create<SplashViewIntent>()
    override fun subscribeIntents(intents: Observable<SplashViewIntent>) = intents.subscribe(intentsSubject)
    override fun intentToAction(intent: SplashViewIntent) =
        when(intent) {
            is SplashViewIntent.InitialIntent -> SplashViewAction.NavigateToHomeAction(intent.duration)
        }
}
