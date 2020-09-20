/*
 * Created by Lee Oh Hyung on 2020/09/20.
 */
package kr.ohyung.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kr.ohyung.remote.Response

@JsonClass(generateAdapter = true)
data class PhotoLinksResponse(
    @field:Json(name = "self")
    val self: String?,
    @field:Json(name = "html")
    val html: String?,
    @field:Json(name = "download")
    val download: String?,
    @field:Json(name = "download_location")
    val download_location: String?
) : Response
