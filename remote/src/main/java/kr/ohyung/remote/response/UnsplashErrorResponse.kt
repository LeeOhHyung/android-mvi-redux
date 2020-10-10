/*
 * Created by Lee Oh Hyung on 2020/10/11.
 */
package kr.ohyung.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kr.ohyung.remote.Response

@JsonClass(generateAdapter = true)
data class UnsplashErrorResponse(
    @field:Json(name = "errors")
    val errors: List<String>
) : Response
