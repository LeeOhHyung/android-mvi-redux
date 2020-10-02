/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.remote

import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.mockito.MockitoAnnotations

abstract class DataSourceTest {

    @Before
    open fun setup() {
        MockitoAnnotations.initMocks(this)
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @After
    open fun tearDown() {
        /* explicitly empty */
    }
}
