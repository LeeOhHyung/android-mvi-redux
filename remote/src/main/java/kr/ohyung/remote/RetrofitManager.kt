package kr.ohyung.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager {

    private const val TAG: String = "RetrofitManager"
    private const val BASE_URL = ""
    private var isDebugMode: Boolean = true
    private const val CONNECT_TIMEOUT: Long = 30L
    private const val WRITE_TIMEOUT: Long = 30L
    private const val READ_TIMEOUT: Long = 30L

    private const val HEADER_NAVER_MAP_CLIENT_ID: String = "X-NCP-APIGW-API-KEY-ID"
    private const val HEADER_NAVER_MAP_CLIENT_SECRET: String = "X-NCP-APIGW-API-KEY"

    fun <T: Api> create(api: Class<T>, isDebug: Boolean): T {
        isDebugMode = isDebug
        return getRetrofit(BASE_URL).create(api)
    }

    private fun getRetrofit(baseUrl: String) : Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getOkHttpClient())
            .build()

    private fun getOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(getHttpLoggerInterceptor())
            .addInterceptor(createNaverMapHeaderInterceptor())
            .build()

    private fun getHttpLoggerInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .apply {
                level =
                    if(isDebugMode)
                        HttpLoggingInterceptor.Level.BODY
                    else
                        HttpLoggingInterceptor.Level.NONE
            }

    private fun createNaverMapHeaderInterceptor(): Interceptor =
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
