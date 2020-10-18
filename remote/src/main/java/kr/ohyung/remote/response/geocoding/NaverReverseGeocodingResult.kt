/*
 * Created by Lee Oh Hyoung on 2020/10/18 ..
 */
package kr.ohyung.remote.response.geocoding

import com.squareup.moshi.Json
import kr.ohyung.remote.Response

data class NaverReverseGeocodingResult(
    @field:Json(name = "name") // 주소타입(AddressType)
    val name: String,
    @field:Json(name = "code")
    val code: CodeResponse,
    @field:Json(name ="region")
    val region: NaverReverseGeocodingRegion
): Response {

    data class CodeResponse(
        @field:Json(name = "id")
        val id: String,
        @field:Json(name = "type")
        val type: String,
        @field:Json(name = "mappingId")
        val mappingId: String
    )
}
