package kr.ohyung.mvi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kr.ohyung.core.mvi.ActionProcessor
import kr.ohyung.core.mvi.IntentProcessor
import kr.ohyung.domain.executor.ExecutorProvider
import kr.ohyung.domain.usecase.GetRandomPhotoUseCase
import kr.ohyung.mvi.splash.mvi.SplashViewAction
import kr.ohyung.mvi.splash.mvi.SplashViewIntent
import kr.ohyung.mvi.splash.mvi.SplashViewResult
import kr.ohyung.mvi.splash.processor.SplashActionProcessor
import kr.ohyung.mvi.splash.processor.SplashIntentProcessor
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ProcessorModule {

    @Provides
    @Singleton
    fun provideSplashIntentProcessor(): IntentProcessor<SplashViewIntent, SplashViewAction> = SplashIntentProcessor()

    @Provides
    @Singleton
    fun provideSplashActionProcessor(
        getRandomPhotoUseCase: GetRandomPhotoUseCase,
        executorProvider: ExecutorProvider
    ): ActionProcessor<SplashViewAction, SplashViewResult> = SplashActionProcessor(getRandomPhotoUseCase, executorProvider)
}
