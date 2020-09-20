/*
 * Created by Lee Oh Hyung on 2020/09/21.
 */
package kr.ohyung.remote.mock

import kr.ohyung.remote.api.PhotosApi
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

internal fun mockPhotosApi(mockWebServer: MockWebServer): PhotosApi =
    Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(PhotosApi::class.java)
