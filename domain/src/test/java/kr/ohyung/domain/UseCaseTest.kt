/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.domain

import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import kr.ohyung.domain.mock.TestExecutors
import org.junit.Before
import org.mockito.MockitoAnnotations

abstract class UseCaseTest {

    protected lateinit var testExecutors: TestExecutors
    
    @Before
    open fun setup() {
        MockitoAnnotations.initMocks(this)
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        testExecutors = TestExecutors()
    }
}
