/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.domain.executor

import io.reactivex.Scheduler

interface ExecutorProvider {
    fun io(): Scheduler
    fun mainThread(): Scheduler
    fun computation(): Scheduler
    fun newThread(): Scheduler
}
