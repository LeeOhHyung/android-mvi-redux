/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.remote.response

import kr.ohyung.remote.Response

data class PhotosResponse(
    val id: String,
    val width: Int,
    val height: Int,
    val color: String,
    val description: String,
    val thumbnail: String,
    val likes: Int,
    val username: String
) : Response
