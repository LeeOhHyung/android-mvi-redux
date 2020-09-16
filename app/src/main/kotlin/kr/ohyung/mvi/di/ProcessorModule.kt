package kr.ohyung.mvi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kr.ohyung.core.mvi.ActionProcessor
import kr.ohyung.core.mvi.IntentProcessor
import kr.ohyung.mvi.splash.mvi.SplashViewAction
import kr.ohyung.mvi.splash.mvi.SplashViewIntent
import kr.ohyung.mvi.splash.processor.SplashActionProcessor
import kr.ohyung.mvi.splash.mvi.SplashViewResult
import kr.ohyung.mvi.splash.processor.SplashIntentProcessor
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
object ProcessorModule {

    @Provides
    @Singleton
    fun provideSplashIntentProcessor(): IntentProcessor<SplashViewIntent, SplashViewAction> = SplashIntentProcessor()

    @Provides
    @Singleton
    fun provideSplashActionProcessor(): ActionProcessor<SplashViewAction, SplashViewResult> = SplashActionProcessor()
}
