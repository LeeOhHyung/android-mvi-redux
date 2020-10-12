package kr.ohyung.mvi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kr.ohyung.core.mvi.ViewStateReducer
import kr.ohyung.mvi.splash.mvi.SplashViewResult
import kr.ohyung.mvi.splash.mvi.SplashViewState
import kr.ohyung.mvi.splash.mvi.SplashViewStateReducer
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ReducerModule {

    @Provides
    @Singleton
    fun provideSplashStateReducer(): ViewStateReducer<SplashViewState, SplashViewResult> = SplashViewStateReducer()
}
