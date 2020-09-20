/*
 * Created by Lee Oh Hyung on 2020/09/21.
 */
package kr.ohyung.remote

import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.mockito.MockitoAnnotations

internal abstract class DataSourceTest {

    @Before
    open fun setup() {
        MockitoAnnotations.initMocks(this)
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }
}
