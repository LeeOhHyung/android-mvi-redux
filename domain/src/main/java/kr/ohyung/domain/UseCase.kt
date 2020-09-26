/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.domain

import io.reactivex.Scheduler

interface UseCase {
    val executorThread: Scheduler
    val postExecutionThread: Scheduler
}
