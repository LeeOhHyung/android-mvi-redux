/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.domain

import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import kr.ohyung.domain.mock.TestExecutors
import org.junit.Before
import org.junit.Rule
import org.junit.rules.ExpectedException
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

abstract class UseCaseTest {

    @get: Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val expectException: ExpectedException = ExpectedException.none()

    protected lateinit var testExecutors: TestExecutors
    
    @Before
    open fun setup() {
        MockitoAnnotations.initMocks(this)
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        testExecutors = TestExecutors()
    }
}
