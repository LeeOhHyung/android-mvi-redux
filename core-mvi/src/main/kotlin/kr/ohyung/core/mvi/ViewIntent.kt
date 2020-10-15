package kr.ohyung.core.mvi

import io.reactivex.subjects.PublishSubject

/**
 * Intention of changing state
 */
interface ViewIntent

fun <T: ViewIntent> ViewIntent.toPublisher() = PublishSubject.create<T>()
fun <T: ViewIntent> ViewIntent.toPublisherWithEmit(item: T): PublishSubject<T> = toPublisher<T>().also { it.onNext(item) }
