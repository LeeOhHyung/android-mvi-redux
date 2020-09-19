/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.data

import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.mockito.MockitoAnnotations

abstract class RepositoryTest {

    @Before
    open fun setup() {
        MockitoAnnotations.initMocks(this)
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }
}
