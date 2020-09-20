/*
 * Created by Lee Oh Hyung on 2020/09/20.
 */
package kr.ohyung.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kr.ohyung.remote.Response

@JsonClass(generateAdapter = true)
data class UserResponse(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "username")
    val username: String,
    @field:Json(name = "portfolio_url")
    val portfolioUrl: String?,
    @field:Json(name = "bio")
    val bio: String?,
    @field:Json(name = "location")
    val location: String?,
    @field:Json(name = "links")
    val links: UserLinksResponse?,
    @field:Json(name = "profile_image")
    val profileImage: UserProfileImageResponse?,
    @field:Json(name = "instagram_username")
    val instagramUsername: String?,
    @field:Json(name = "total_collections")
    val totalCollections: Int,
    @field:Json(name = "total_likes")
    val totalLikes: Int,
    @field:Json(name = "total_photos")
    val totalPhotos: Int,
    @field:Json(name = "accepted_tos")
    val acceptedTos: Boolean
) : Response {

    @JsonClass(generateAdapter = true)
    data class UserLinksResponse(
        @field:Json(name = "self")
        val self: String?,
        @field:Json(name = "html")
        val html: String?,
        @field:Json(name = "photos")
        val photos: String?,
        @field:Json(name = "likes")
        val likes: String?,
        @field:Json(name = "portfolio")
        val portfolio: String?,
        @field:Json(name = "following")
        val following: String?,
        @field:Json(name = "followers")
        val followers: String?
    ) : Response

    @JsonClass(generateAdapter = true)
    data class UserProfileImageResponse(
        @field:Json(name = "small")
        val small: String?,
        @field:Json(name = "medium")
        val medium: String?,
        @field:Json(name = "large")
        val large: String?
    ) : Response
}
