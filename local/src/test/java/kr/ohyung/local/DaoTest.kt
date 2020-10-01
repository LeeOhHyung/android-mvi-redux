/*
 * Created by Lee Oh Hyung on 2020/10/01.
 */
package kr.ohyung.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest= Config.NONE)
@RunWith(RobolectricTestRunner::class)
internal abstract class DaoTest<R: RoomDatabase> {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    protected lateinit var database: R

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            database.javaClass)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun tearDown() {
        database.close()
    }
}
