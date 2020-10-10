package kr.ohyung.mvi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.ohyung.domain.executor.ExecutorProvider
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideExecutorProvider(): ExecutorProvider = object : ExecutorProvider {
        override fun io(): Scheduler = Schedulers.io()
        override fun main(): Scheduler = AndroidSchedulers.mainThread()
        override fun computation(): Scheduler = Schedulers.computation()
        override fun newThread(): Scheduler = Schedulers.newThread()
    }
}
