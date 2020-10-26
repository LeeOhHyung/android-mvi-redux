package kr.ohyung.mvi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kr.ohyung.core.mvi.ViewStateReducer
import kr.ohyung.mvi.home.mvi.HomeViewResult
import kr.ohyung.mvi.home.mvi.HomeViewState
import kr.ohyung.mvi.home.mvi.HomeViewStateReducer
import kr.ohyung.mvi.splash.mvi.SplashViewResult
import kr.ohyung.mvi.splash.mvi.SplashViewState
import kr.ohyung.mvi.splash.mvi.SplashViewStateReducer

@Module
@InstallIn(ActivityRetainedComponent::class)
object ReducerModule {

    @Provides
    @ActivityRetainedScoped
    fun provideSplashStateReducer(): ViewStateReducer<SplashViewState, SplashViewResult> = SplashViewStateReducer()

    @Provides
    @ActivityRetainedScoped
    fun provideHomeStateReducer(): ViewStateReducer<HomeViewState, HomeViewResult> = HomeViewStateReducer()
}
