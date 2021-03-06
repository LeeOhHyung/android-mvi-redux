/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.remote.api

import io.reactivex.Single
import kr.ohyung.remote.Api
import kr.ohyung.remote.BuildConfig
import kr.ohyung.remote.response.PhotosResponse
import kr.ohyung.remote.response.SearchPhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi : Api {

    @GET("photos")
    fun getPhotos(
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
        @Query("order_by") orderBy: String?,
        @Query("client_id") clientId: String = BuildConfig.API_KEY_UNSPLASH
    ): Single<List<PhotosResponse>>

    @GET("photos/random")
    fun getRandomPhoto(
        @Query("query") query: String?,
        @Query("client_id") clientId: String = BuildConfig.API_KEY_UNSPLASH
    ): Single<PhotosResponse>

    @GET("search/photos")
    fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int?, // default 1
        @Query("per_page") perPage: Int?, // default 10
        @Query("client_id") clientId: String = BuildConfig.API_KEY_UNSPLASH
    ): Single<SearchPhotoResponse>
}
