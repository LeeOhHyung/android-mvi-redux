/*
 * Created by Lee Oh Hyoung on 2020/10/18 ..
 */
package kr.ohyung.remote.response.geocoding

import com.squareup.moshi.Json
import kr.ohyung.remote.Response

data class NaverReverseGeocodingArea(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "coords")
    val coords: CoordsResponse
): Response {

    data class CoordsResponse(
        @field:Json(name = "center")
        val center: CenterResponse
    ): Response {

        data class CenterResponse(
            @field:Json(name = "crs") // 좌표계
            val crs: String,

            @field:Json(name = "x") // 경도(longitude)
            val longitude: Double,

            @field:Json(name = "y") // 위도(latitude)
            val latitude: Double
        ): Response
    }
}
