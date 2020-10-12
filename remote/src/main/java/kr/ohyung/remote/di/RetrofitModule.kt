package kr.ohyung.remote.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kr.ohyung.remote.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    private const val TAG: String = "RetrofitModule"
    private const val CONNECT_TIMEOUT: Long = 30L
    private const val WRITE_TIMEOUT: Long = 30L
    private const val READ_TIMEOUT: Long = 30L

    private const val HEADER_NAVER_MAP_CLIENT_ID: String = "X-NCP-APIGW-API-KEY-ID"
    private const val HEADER_NAVER_MAP_CLIENT_SECRET: String = "X-NCP-APIGW-API-KEY"

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()


    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggerInterceptor: HttpLoggingInterceptor,
        headerInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggerInterceptor)
            .addInterceptor(headerInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideHttpLoggerInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.e(TAG, message)
            }
        }).apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun provideHeaderIntercept(): Interceptor =
        object: Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response =
                chain.proceed(
                    chain.request().newBuilder()
                        .addHeader(HEADER_NAVER_MAP_CLIENT_ID, "") // Github Public Repo 에서도 안전하게 Key 전달하는 방법 찾아보기
                        .addHeader(HEADER_NAVER_MAP_CLIENT_SECRET, "")
                        .build()
                )
        }
}
