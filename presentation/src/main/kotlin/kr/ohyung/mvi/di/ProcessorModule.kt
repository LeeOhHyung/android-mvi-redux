package kr.ohyung.mvi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kr.ohyung.core.mvi.ActionProcessor
import kr.ohyung.core.mvi.IntentProcessor
import kr.ohyung.domain.executor.ExecutorProvider
import kr.ohyung.domain.usecase.GetCurrentLocationForecastUseCase
import kr.ohyung.domain.usecase.GetRandomPhotoUseCase
import kr.ohyung.domain.usecase.SearchPhotoUseCase
import kr.ohyung.mvi.home.mvi.HomeViewAction
import kr.ohyung.mvi.home.mvi.HomeViewIntent
import kr.ohyung.mvi.home.mvi.HomeViewResult
import kr.ohyung.mvi.home.processor.HomeActionProcessor
import kr.ohyung.mvi.home.processor.HomeIntentProcessor
import kr.ohyung.mvi.splash.mvi.SplashViewAction
import kr.ohyung.mvi.splash.mvi.SplashViewIntent
import kr.ohyung.mvi.splash.mvi.SplashViewResult
import kr.ohyung.mvi.splash.processor.SplashActionProcessor
import kr.ohyung.mvi.splash.processor.SplashIntentProcessor

@Module
@InstallIn(ActivityRetainedComponent::class)
object ProcessorModule {

    @Provides
    @ActivityRetainedScoped
    fun provideSplashIntentProcessor(): IntentProcessor<SplashViewIntent, SplashViewAction> = SplashIntentProcessor()

    @Provides
    @ActivityRetainedScoped
    fun provideSplashActionProcessor(
        getRandomPhotoUseCase: GetRandomPhotoUseCase,
        executorProvider: ExecutorProvider
    ): ActionProcessor<SplashViewAction, SplashViewResult> = SplashActionProcessor(getRandomPhotoUseCase, executorProvider)

    @Provides
    @ActivityRetainedScoped
    fun provideHomeIntentProcessor(): IntentProcessor<HomeViewIntent, HomeViewAction> = HomeIntentProcessor()

    @Provides
    @ActivityRetainedScoped
    fun provideHomeActionProcessor(
        getCurrentLocationForecastUseCase: GetCurrentLocationForecastUseCase,
        searchPhotoUseCase: SearchPhotoUseCase,
        executorProvider: ExecutorProvider
    ): ActionProcessor<HomeViewAction, HomeViewResult> = HomeActionProcessor(getCurrentLocationForecastUseCase, searchPhotoUseCase, executorProvider)
}
