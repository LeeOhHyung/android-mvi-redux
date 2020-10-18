/*
 * Created by Lee Oh Hyung on 2020/10/18.
 */
package kr.ohyung.remote.api

import io.reactivex.Single
import kr.ohyung.remote.Api
import kr.ohyung.remote.response.geocoding.NaverReverseGeocodingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ReverseGeocodingApi : Api {

    @GET("gc")
    fun getLegalNameByLatLon(
        @Query("coords") coords: String, // longtitude,latitude
        @Query("output") output: String
    ): Single<NaverReverseGeocodingResponse>
}
