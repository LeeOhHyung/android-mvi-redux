package kr.ohyung.mvi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ApplicationComponent
import kr.ohyung.core.mvi.ActionProcessor
import kr.ohyung.core.mvi.IntentProcessor
import kr.ohyung.mvi.splash.mvi.SplashAction
import kr.ohyung.mvi.splash.mvi.SplashIntent
import kr.ohyung.mvi.splash.processor.SplashActionProcessor
import kr.ohyung.mvi.splash.mvi.SplashResult
import kr.ohyung.mvi.splash.processor.SplashIntentProcessor
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
object ProcessorModule {

    @Provides
    @Singleton
    fun provideSplashIntentProcessor(): IntentProcessor<SplashIntent, SplashAction> = SplashIntentProcessor()

    @Provides
    @Singleton
    fun provideSplashActionProcessor(): ActionProcessor<SplashAction, SplashResult> = SplashActionProcessor()
}
