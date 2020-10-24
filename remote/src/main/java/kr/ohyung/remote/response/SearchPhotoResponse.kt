/*
 * Created by Lee Oh Hyung on 2020/10/24.
 */
package kr.ohyung.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kr.ohyung.remote.Response

@JsonClass(generateAdapter = true)
data class SearchPhotoResponse(
    @field:Json(name = "total")
    val total: Int,
    @field:Json(name = "total_pages")
    val total_pages: Int,
    @field:Json(name = "results")
    val results: List<PhotosResponse>
) : Response
