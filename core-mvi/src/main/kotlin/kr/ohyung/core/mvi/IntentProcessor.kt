/*
 * Created by Lee Oh Hyung on 2020/09/13.
 */
package kr.ohyung.core.mvi

import io.reactivex.subjects.PublishSubject

interface IntentProcessor<I: ViewIntent, A: ViewAction> {
    val intentsSubject: PublishSubject<I>
    fun toAction(intent: I): A
}
