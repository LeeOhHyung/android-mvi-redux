package kr.ohyung.mvi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kr.ohyung.core.mvi.Processor
import kr.ohyung.mvi.splash.SplashAction
import kr.ohyung.mvi.splash.SplashProcessor
import kr.ohyung.mvi.splash.SplashResult
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ProcessorModule {

    @Provides
    @Singleton
    fun provideSplashProcessor(): Processor<SplashAction, SplashResult> = SplashProcessor()
}
