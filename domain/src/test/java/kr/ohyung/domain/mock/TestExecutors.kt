/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.domain.mock

import io.reactivex.schedulers.Schedulers
import kr.ohyung.domain.executor.ExecutorProvider

class TestExecutors : ExecutorProvider {
    override fun io() = Schedulers.trampoline()
    override fun mainThread() = Schedulers.trampoline()
    override fun computation() = Schedulers.trampoline()
    override fun newThread() = Schedulers.trampoline()
}
