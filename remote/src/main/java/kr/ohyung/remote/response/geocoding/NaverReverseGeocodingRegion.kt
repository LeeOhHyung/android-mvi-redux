/*
 * Created by Lee Oh Hyoung on 2020/10/18 ..
 */
package kr.ohyung.remote.response.geocoding

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kr.ohyung.remote.Response

@JsonClass(generateAdapter = true)
data class NaverReverseGeocodingRegion(
    @field:Json(name ="area0") // 국가 코드
    val area0: NaverReverseGeocodingArea,

    @field:Json(name = "area1") // 특별시,광역시,도
    val area1: NaverReverseGeocodingArea,

    @field:Json(name="area2") // 시군구
    val area2: NaverReverseGeocodingArea,

    @field:Json(name = "area3") // 읍면동
    val area3: NaverReverseGeocodingArea,

    @field:Json(name = "area4") // 리
    val area4: NaverReverseGeocodingArea
) : Response
