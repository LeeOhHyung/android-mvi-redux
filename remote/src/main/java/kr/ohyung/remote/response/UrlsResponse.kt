/*
 * Created by Lee Oh Hyung on 2020/09/20.
 */
package kr.ohyung.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kr.ohyung.remote.Response

@JsonClass(generateAdapter = true)
data class UrlsResponse(
    @field:Json(name = "raw")
    val raw: String?,
    @field:Json(name = "full")
    val full: String?,
    @field:Json(name = "regular")
    val regular: String?,
    @field:Json(name = "small")
    val small: String?,
    @field:Json(name = "thumb")
    val thumb: String?
) : Response
