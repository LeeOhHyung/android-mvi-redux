/*
 * Created by Lee Oh Hyoung on 2020/10/18 ..
 */
package kr.ohyung.remote.response.geocoding

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kr.ohyung.remote.Response

@JsonClass(generateAdapter = true)
data class NaverReverseGeocodingStatus(

    @field:Json(name = "code")
    val code: Int,

    @field:Json(name ="name")
    val name: String,

    @field:Json(name = "message")
    val message: String
) : Response
