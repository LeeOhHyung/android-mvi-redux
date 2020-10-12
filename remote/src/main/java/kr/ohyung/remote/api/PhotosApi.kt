/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.remote.api

import io.reactivex.Single
import kr.ohyung.remote.Api
import kr.ohyung.remote.BuildConfig
import kr.ohyung.remote.response.PhotosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi : Api {

    @GET("photos")
    fun getPhotos(
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
        @Query("order_by") orderBy: String?
    ): Single<List<PhotosResponse>>

    @GET("photos/random")
    fun getRandomPhoto(
        @Query("query") query: String?,
        @Query("client_id") clientId: String? = BuildConfig.clientId
    ): Single<PhotosResponse>
}
