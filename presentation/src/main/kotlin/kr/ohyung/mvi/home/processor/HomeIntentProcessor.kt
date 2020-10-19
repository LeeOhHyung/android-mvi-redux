/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.mvi.home.processor

import io.reactivex.subjects.PublishSubject
import kr.ohyung.core.mvi.IntentProcessor
import kr.ohyung.mvi.home.mvi.HomeViewAction
import kr.ohyung.mvi.home.mvi.HomeViewIntent
import javax.inject.Inject

class HomeIntentProcessor @Inject constructor() : IntentProcessor<HomeViewIntent, HomeViewAction> {
    override val intentsSubject = PublishSubject.create<HomeViewIntent>()
    override fun toAction(intent: HomeViewIntent) =
        when(intent) {
            HomeViewIntent.Noting -> HomeViewAction.Nothing
            HomeViewIntent.Retry -> HomeViewAction.GetLocationAndPhotos
            HomeViewIntent.InitHomeScreen -> HomeViewAction.GetLocationAndPhotos
            is HomeViewIntent.AddBookmark -> HomeViewAction.AddBookmark(bookmark = intent.bookmark)
            is HomeViewIntent.DeleteBookmark -> HomeViewAction.DeleteBookmark(id = intent.id)
        }
}
