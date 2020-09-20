/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kr.ohyung.remote.Response

@JsonClass(generateAdapter = true)
data class PhotosResponse(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "width")
    val width: Int,
    @field:Json(name = "height")
    val height: Int,
    @field:Json(name = "color")
    val color: String,
    @field:Json(name = "description")
    val description: String?,
    @field:Json(name = "alt_description")
    val alt_description: String?,
    @field:Json(name = "urls")
    val urls: UrlsResponse,
    @field: Json(name = "links")
    val links: PhotoLinksResponse,
    @field:Json(name = "likes")
    val likes: Int,
    @field:Json(name = "user")
    val user: UserResponse
) : Response
